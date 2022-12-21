package com.example.LaptopStoreAPI.services;

import com.example.LaptopStoreAPI.models.Product;

import java.util.List;

public interface IProductService {
    Product findById(String id);
    List<Product> findAll();
    Product delete(String id);

}
