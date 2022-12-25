package com.example.LaptopStoreAPI.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class RequestUtils {
    public static String getAuthorizationTokenFromRequest(HttpServletRequest request) {
        var authorization = request.getHeader("Authorization");
        return authorization != null ? StringUtils.emptyToNull(authorization.trim().replace("Bearer", "").trim()) : null;
    }

    public static String getCookieFromRequest(String cookieName, HttpServletRequest request) {
        var cookies = request.getCookies();
        var result = Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals(cookieName))
                .findFirst()
                .orElse(null);
        return result != null ? result.getValue() : null;
    }
}
