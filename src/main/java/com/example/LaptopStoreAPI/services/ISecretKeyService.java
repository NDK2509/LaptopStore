package com.example.LaptopStoreAPI.services;

import com.example.LaptopStoreAPI.models.SecretKey;

public interface ISecretKeyService {
    SecretKey findByUserId(String userId);
    void delete(SecretKey token);
    SecretKey save(SecretKey token);
    ITokenHandler getTokenHandler();
}
