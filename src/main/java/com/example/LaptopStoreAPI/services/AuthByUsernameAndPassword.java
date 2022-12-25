package com.example.LaptopStoreAPI.services;

import com.example.LaptopStoreAPI.configs.EnvConfig;
import com.example.LaptopStoreAPI.models.SecretKey;
import com.example.LaptopStoreAPI.models.User;
import com.example.LaptopStoreAPI.payloads.LoginWithUsernamePasswordPayload;
import com.example.LaptopStoreAPI.resposes.ApiResponse;
import com.example.LaptopStoreAPI.resposes.models.AuthToken;
import com.example.LaptopStoreAPI.services.enums.TokenType;
import com.example.LaptopStoreAPI.utils.JWTUtils;
import com.example.LaptopStoreAPI.utils.RequestUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class AuthByUsernameAndPassword extends IAuthService<ApiResponse<AuthToken>, LoginWithUsernamePasswordPayload> {
    @Autowired
    public AuthByUsernameAndPassword(IUserService userService, ISecretKeyService secretKeyService, EnvConfig env) {
        super(userService, secretKeyService, env);
    }

    private ResponseEntity<ApiResponse<AuthToken>> handleTokens(User user, String messageIfSuccess) {
        try {
            var secret = JWTUtils.generateRandomRefreshSecret(32);
            var accessToken = secretKeyService.getTokenHandler().generateAccessToken(user);
            var refreshToken = secretKeyService.getTokenHandler().generateRefreshToken(user, secret);

            // save the secret to DB
            secretKeyService.save(new SecretKey(user.getUserId(), secret));

            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.SET_COOKIE, JWTUtils.getJWTCookie(accessToken, TokenType.ACCESS))
                    .header(HttpHeaders.SET_COOKIE, JWTUtils.getJWTCookie(refreshToken, TokenType.REFRESH))
                    .body(
                            new ApiResponse<>(
                                    new AuthToken(accessToken, refreshToken),
                                    messageIfSuccess
                            )
                    );
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse<>(null, "Please try again!" + e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<ApiResponse<AuthToken>> login(LoginWithUsernamePasswordPayload reqBody) {
        var user = userService.findByUsername(reqBody.getUsername());
        if (user == null)
            return new ResponseEntity<>(new ApiResponse<>(null, "User is not found!"), HttpStatus.NOT_FOUND);
        if (!BCrypt.checkpw(reqBody.getPassword(), user.getPassword()))
            return new ResponseEntity<>(new ApiResponse<>(null, "Password wrong!"), HttpStatus.NOT_ACCEPTABLE);

        // handle token
        return handleTokens(user, "Login successfully!");
    }

    @Override
    public ResponseEntity<ApiResponse<AuthToken>> refreshToken(HttpServletRequest request) {
        // User need to send thr refresh token with their userId
        var refToken = RequestUtils.getAuthorizationTokenFromRequest(request);
        if (refToken != null) {
            var decodedJWT = JWTUtils.getDecodedJWT(refToken);
            var userId = decodedJWT.getSubject();
            var secret = secretKeyService.findByUserId(userId);
            if (secret != null) {
                if (secretKeyService.getTokenHandler().verifyToken(refToken, secret.getRefreshSecret())) {
                    var exp = decodedJWT.getExpiresAt();
                    var now = new Date(new Date().getTime() + env.getGMTOffset());
                    if (exp.compareTo(now) >= 0) {
                        return handleTokens(userService.findById(userId), "Refresh successfully!");
                    }
                    return new ResponseEntity<>(new ApiResponse<>(null, "Refresh token has expired!!"), HttpStatus.NOT_FOUND);
                }
                return ResponseEntity.ok().body(new ApiResponse<>(null, "Invalid token!"));
            }
            return new ResponseEntity<>(new ApiResponse<>(null, "Secret key is not found!"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ApiResponse<>(null, "Refresh token is not found!"), HttpStatus.NOT_FOUND);
    }
}
