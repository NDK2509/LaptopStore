package com.example.LaptopStoreAPI.services;

import com.example.LaptopStoreAPI.payloads.AuthPayload;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface IAuthHandler {
    void setAuthService(IAuthService authenticationService);
    ResponseEntity login(AuthPayload payload);
    ResponseEntity refreshToken(HttpServletRequest request);
}
