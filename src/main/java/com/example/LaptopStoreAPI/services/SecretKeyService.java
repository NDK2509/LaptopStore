package com.example.LaptopStoreAPI.services;

import com.example.LaptopStoreAPI.models.SecretKey;
import com.example.LaptopStoreAPI.repositories.ITokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SecretKeyService implements ISecretKeyService {
    private ITokenRepository repository;
    private ITokenHandler tokenHandler;
    @Override
    public SecretKey findByUserId(String userId) {
        return repository.findByUserId(userId).orElse(null);
    }

    @Override
    public void delete(SecretKey token) {
        repository.delete(token);
    }

    @Override
    public SecretKey save(SecretKey token) {
        return repository.save(token);
    }
    @Override
    public ITokenHandler getTokenHandler() {
        return tokenHandler;
    }
}
