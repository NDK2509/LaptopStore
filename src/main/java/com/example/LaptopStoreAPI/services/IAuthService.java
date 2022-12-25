package com.example.LaptopStoreAPI.services;

import com.example.LaptopStoreAPI.configs.EnvConfig;
import com.example.LaptopStoreAPI.payloads.AuthPayload;
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
    protected IUserService userService;
    protected ISecretKeyService secretKeyService;
    protected EnvConfig env;

    public abstract ResponseEntity<T> login(R payload);
    public abstract ResponseEntity<T> refreshToken(HttpServletRequest request);
}
