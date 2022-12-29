package com.example.LaptopStoreAPI.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface ApiController<T, M> {
    ResponseEntity<T> getAll();
    ResponseEntity<T> getById(String id);
    ResponseEntity<T> create(M model);
    ResponseEntity<T> update(String id, M model);
    ResponseEntity<T> delete(String id);
}
