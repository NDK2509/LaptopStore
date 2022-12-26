package com.example.LaptopStoreAPI.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.LaptopStoreAPI.configs.EnvConfig;
import com.example.LaptopStoreAPI.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class JWTHandler implements ITokenHandler<User> {
    private final EnvConfig env;
    private final Algorithm accessTokenAlg;
    @Autowired
    public JWTHandler(EnvConfig env) {
        this.env = env;
        this.accessTokenAlg = Algorithm.HMAC512(env.getAccessTokenSecret());
    }

    @Override
    public String generateAccessToken(User user) {
        var now = new Date();
        try {
            return JWT.create()
                    .withSubject(user.getUserId())
                    .withIssuer("auth0")
                    .withClaim("createdDate", now.getTime() + env.getGMTOffset() * 1000L)
                    .withExpiresAt(new Date(now.getTime() + (env.getAccessTokenExpiredTime() + env.getGMTOffset())* 1000L))
                    .sign(accessTokenAlg);
        } catch (JWTCreationException e) {
            throw e;
        }
    }

    @Override
    public String generateRefreshToken(User user, String secret) {
        var now = new Date();
        try {
            return JWT.create()
                    .withSubject(user.getUserId())
                    .withIssuer("auth0")
                    .withClaim("createdDate", now.getTime() + env.getGMTOffset() * 1000L)
                    .withExpiresAt(new Date(now.getTime() + (env.getRefreshTokenExpiredTime() + env.getGMTOffset())* 1000L))
                    .sign(Algorithm.HMAC512(secret));
        } catch (JWTCreationException e) {
            throw e;
        }
    }

    @Override
    public boolean verifyToken(String token, String secret) {
        try {
            Algorithm.HMAC512(secret).verify(JWT.decode(token));
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }
}
