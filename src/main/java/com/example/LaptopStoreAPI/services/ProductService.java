package com.example.LaptopStoreAPI.services;

import com.example.LaptopStoreAPI.models.Product;
import com.example.LaptopStoreAPI.repositories.IProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService implements IProductService{
    private IProductRepository repository;
    @Override
    public Product findById(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public Product delete(String id) {
        var product = this.findById(id);
        if (product != null) repository.delete(product);
        return product;
    }
}
