package com.example.LaptopStoreAPI.services;

public interface ITokenHandler<T> {
    String generateAccessToken(T payload);
    String generateRefreshToken(T payload, String secret);
    boolean verifyToken(String token, String secret);
}
