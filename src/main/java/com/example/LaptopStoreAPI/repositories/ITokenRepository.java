package com.example.LaptopStoreAPI.repositories;

import com.example.LaptopStoreAPI.models.SecretKey;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ITokenRepository extends MongoRepository<SecretKey, String> {
    Optional<SecretKey> findByUserId(String userId);
    void delete(SecretKey token);
    SecretKey save(SecretKey token);
}
