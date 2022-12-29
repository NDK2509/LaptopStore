package com.example.LaptopStoreAPI.controllers;

import com.example.LaptopStoreAPI.models.User;
import com.example.LaptopStoreAPI.payloads.RegistrationPayload;
import com.example.LaptopStoreAPI.resposes.ApiResponse;
import com.example.LaptopStoreAPI.services.IUserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

@RestController
@RequestMapping("api/")
@CrossOrigin("*")
@AllArgsConstructor
public class RegistrationApiController {
    protected IUserService userService;

    @PostMapping("register")
    public ResponseEntity<ApiResponse<?>> register(@RequestBody @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            var errors = new HashMap<>();
            result.getAllErrors().forEach(error -> errors.put(Arrays.stream(Objects.requireNonNull(error.getCodes())).toList().get(1), error.getDefaultMessage()));
            return new ResponseEntity<>(new ApiResponse<>(errors, "Validation errors!"), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        if (userService.existsByEmail(user.getEmail())) {
            return new ResponseEntity<>(new ApiResponse<>(null, "This email already exists!"), HttpStatus.NOT_FOUND);
        }
        if (userService.existsByUsername(user.getUsername())) {
            return new ResponseEntity<>(new ApiResponse<>(null, "This username already exists!"), HttpStatus.NOT_FOUND);
        }

        // add user to DB
        var hashedPw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10));
        userService.save(new User(user.getUsername(), hashedPw, user.getEmail()));

        return new ResponseEntity<>(new ApiResponse<>(null, "Create user successfully!"), HttpStatus.OK);
    }
}
