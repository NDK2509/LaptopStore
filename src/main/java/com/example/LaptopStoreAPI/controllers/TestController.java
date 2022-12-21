package com.example.LaptopStoreAPI.controllers;

import com.example.LaptopStoreAPI.resposes.ApiResponse;
import com.example.LaptopStoreAPI.services.IAuthService;
import com.example.LaptopStoreAPI.utils.JWTUtils;
import com.example.LaptopStoreAPI.utils.RequestHelper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TestController {
    private IAuthService authenticationService;
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private RequestHelper requestHelper;

    @GetMapping("/test-receive-cookie")
    public ResponseEntity<?> getHello(HttpServletRequest request) {
        return ResponseEntity.ok().body(new ApiResponse<>(request.getCookies(), ""));
//        return authenticationService.refreshToken(request);
//        return new ResponseEntity<>("Hi, my name is Ky:  " + requestHelper.getAuthorizationTokenFromRequest(request), HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<?> getTest() {
        return ResponseEntity
                .ok()
                .header(
                    HttpHeaders.SET_COOKIE,
                    ResponseCookie
                        .from("test-cookie", "NDK")
                        .httpOnly(true)
                        .maxAge(10000)
                        .build()
                        .toString()
                )
                .body(new ApiResponse<>(null, "Test cookie"));

    }
}
