package com.example.LaptopStoreAPI.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class RequestUtils {
    public static String getAuthorizationTokenFromRequest(HttpServletRequest request) {
        var authorization = request.getHeader("Authorization");
        return authorization != null ? authorization.replace("Bearer ", "") : null;
    }
}
