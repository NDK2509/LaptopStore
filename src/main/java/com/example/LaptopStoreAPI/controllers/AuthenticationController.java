package com.example.LaptopStoreAPI.controllers;

import com.example.LaptopStoreAPI.payloads.LoginWithUsernamePasswordPayload;
import com.example.LaptopStoreAPI.resposes.models.AuthToken;
import com.example.LaptopStoreAPI.services.AuthByUsernameAndPassword;
import com.example.LaptopStoreAPI.services.IAuthHandler;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
@AllArgsConstructor
public class AuthenticationController {
    protected IAuthHandler authHandler;
    protected AuthByUsernameAndPassword authByUsernameAndPassword;

    @PostMapping("login")
    public ResponseEntity<AuthToken> login(@RequestBody LoginWithUsernamePasswordPayload loginRB) {
        authHandler.setAuthService(authByUsernameAndPassword);
        return authHandler.login(loginRB);
    }

    @PostMapping("refresh-token")
    public ResponseEntity<?> refreshToken(HttpServletRequest request) {
//        return authenticationService.refreshToken(request);
        return authHandler.refreshToken(request);
    }
}
