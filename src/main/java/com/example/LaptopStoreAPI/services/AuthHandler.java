package com.example.LaptopStoreAPI.services;

import com.example.LaptopStoreAPI.payloads.AuthPayload;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AuthHandler implements IAuthHandler {
    private IAuthService authenticationService;

    @Override
    public void setAuthService(IAuthService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    public ResponseEntity login(AuthPayload payload) {
        return authenticationService.login(payload);
    }

    @Override
    public ResponseEntity refreshToken(HttpServletRequest request) {
        return null;
    }
}
