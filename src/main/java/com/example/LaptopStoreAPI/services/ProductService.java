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
    public List<Product> findAll() {
        return repository.findAll();
    }
    @Override
    public Product findById(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Product create(Product product) {
        return repository.save(product);
    }

    @Override
    public Product update(Product product) {
        return repository.save(product);
    }

    @Override
    public Product delete(String id) {
        var product = this.findById(id);
        if (product != null) repository.delete(product);
        return product;
    }

    @Override
    public boolean exitsById(String id) {
        return repository.existsById(id);
    }
}
