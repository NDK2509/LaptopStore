package com.example.LaptopStoreAPI.middlewares;

import com.example.LaptopStoreAPI.configs.EnvConfig;
import com.example.LaptopStoreAPI.services.ITokenHandler;
import com.example.LaptopStoreAPI.utils.JWTUtils;
import com.example.LaptopStoreAPI.utils.RequestUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.util.Optional;

@WebFilter(urlPatterns = {"/api/v1/*"})
@AllArgsConstructor
public class AuthFilter extends HttpFilter {
    private ITokenHandler jwtHandler;
    private EnvConfig env;
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        var token =
                Optional
                        .ofNullable(RequestUtils.getAuthorizationTokenFromRequest(request))
                        .orElse(RequestUtils.getCookieFromRequest(JWTUtils.JWT_ACCESS_COOKIE_NAME, request));
        if (token == null) {
            response.setContentType("application/json");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Required access token!");
            return;
        }
        if (jwtHandler.verifyToken(token, env.getAccessTokenSecret())) {
            chain.doFilter(request, response);
        }
        else {
            response.setContentType("application/json");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access token invalid!");
        }
    }
}
