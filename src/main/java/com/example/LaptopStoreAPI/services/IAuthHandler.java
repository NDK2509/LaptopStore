package com.example.LaptopStoreAPI.services;

import com.example.LaptopStoreAPI.payloads.AuthPayload;
import com.example.LaptopStoreAPI.resposes.ApiResponse;
import com.example.LaptopStoreAPI.resposes.models.AuthToken;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface IAuthHandler {
    void setAuthService(IAuthService authenticationService);
    ResponseEntity<ApiResponse<AuthToken>> login(AuthPayload payload);
    ResponseEntity<ApiResponse<AuthToken>> refreshToken(HttpServletRequest request);
}