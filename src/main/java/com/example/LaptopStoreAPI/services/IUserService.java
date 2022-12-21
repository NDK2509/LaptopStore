package com.example.LaptopStoreAPI.services;

import com.example.LaptopStoreAPI.models.User;

public interface IUserService {
    User findById(String id);
    User findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    User save(User user);
}
