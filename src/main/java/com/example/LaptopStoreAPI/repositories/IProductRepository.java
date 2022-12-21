package com.example.LaptopStoreAPI.repositories;

import com.example.LaptopStoreAPI.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IProductRepository extends MongoRepository<Product, String> {
    Optional<Product> findById(String id);
    Product save(Product product);
    void delete(Product product);
}
