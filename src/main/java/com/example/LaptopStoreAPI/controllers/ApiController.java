package com.example.LaptopStoreAPI.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface ApiController<T, M> {
    ResponseEntity<T> getAll(HttpServletRequest request);
    ResponseEntity<T> getOneById(HttpServletRequest request, String id);
    ResponseEntity<T> save(HttpServletRequest request, M model);
    ResponseEntity<T> delete(HttpServletRequest request, String id);
}
