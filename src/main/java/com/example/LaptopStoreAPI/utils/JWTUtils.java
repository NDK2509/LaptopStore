package com.example.LaptopStoreAPI.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.LaptopStoreAPI.services.enums.TokenType;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Random;

@Component
public class JWTUtils {
    public static final String JWT_ACCESS_COOKIE_NAME = "jwt-access-cookie";
    public static final String JWT_REFRESH_COOKIE_NAME = "jwt-refresh-cookie";

    public static DecodedJWT getDecodedJWT(String token) {
        return JWT.decode(token);
    }


    public static String getJWTCookie(String token, TokenType type) {
        var decodedJwt = getDecodedJWT(token);
        var expriedDate = decodedJwt.getExpiresAt();
        switch (type) {
            default -> {
                return null;
            }
            case ACCESS -> {
                return ResponseCookie
                        .from(JWT_ACCESS_COOKIE_NAME, token)
                        .httpOnly(true)
                        .maxAge((expriedDate.getTime() - new Date().getTime())/1000)
                        .path("/api/v1")
                        .build()
                        .toString();
            }
            case REFRESH -> {
                return ResponseCookie
                        .from(JWT_REFRESH_COOKIE_NAME, token)
                        .httpOnly(true)
                        .maxAge((expriedDate.getTime() - new Date().getTime())/1000)
                        .path("/api/refresh-token")
                        .build()
                        .toString();
            }
        }
    }

    public static String generateRandomRefreshSecret(int length) {
        var alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        var alphabetLen = alphabet.length();
        var ran = new Random();
        var result = new StringBuilder();

        for (int i = 0; i < length; i++) {
            result.append(alphabet.charAt(ran.nextInt(alphabetLen)));
        }

        return result.toString();
    }
}
