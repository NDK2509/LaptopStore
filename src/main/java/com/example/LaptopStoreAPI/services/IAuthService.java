package com.example.LaptopStoreAPI.services;

import com.example.LaptopStoreAPI.configs.EnvConfig;
import com.example.LaptopStoreAPI.utils.JWTUtils;
import com.example.LaptopStoreAPI.utils.RequestHelper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@AllArgsConstructor
public abstract class IAuthService<T, R> {
    @Autowired
    protected IUserService userService;
    @Autowired
    protected ISecretKeyService secretKeyService;
    @Autowired
    protected JWTUtils jwtUtils;
    @Autowired
    protected RequestHelper requestHelper;
    @Autowired
    protected EnvConfig env;

    public abstract ResponseEntity<T> login(R requestBody);
    public abstract ResponseEntity<T> refreshToken(HttpServletRequest request);
}
