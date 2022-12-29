package com.example.LaptopStoreAPI.services;

import com.example.LaptopStoreAPI.models.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();
    Product findById(String id);
    Product create(Product product);
    Product update(Product product);
    Product delete(String id);
    boolean exitsById(String id);
}
