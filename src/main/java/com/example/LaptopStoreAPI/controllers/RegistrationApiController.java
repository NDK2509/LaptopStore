package com.example.LaptopStoreAPI.controllers;

import com.example.LaptopStoreAPI.models.User;
import com.example.LaptopStoreAPI.payloads.RegistrationPayload;
import com.example.LaptopStoreAPI.resposes.ApiResponse;
import com.example.LaptopStoreAPI.services.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/")
@CrossOrigin("*")
@AllArgsConstructor
public class RegistrationApiController {
    protected IUserService userService;

    @PostMapping("register")
    public ResponseEntity<ApiResponse> register(@RequestBody RegistrationPayload registrationPayload) {
        if (userService.existsByEmail(registrationPayload.getEmail())) {
            return new ResponseEntity<>(new ApiResponse(null, "This email already exists!"), HttpStatus.NOT_FOUND);
        }
        if (userService.existsByUsername(registrationPayload.getUsername())) {
            return new ResponseEntity<>(new ApiResponse(null, "This username already exists!"), HttpStatus.NOT_FOUND);
        }

        // add user to DB
        var hashedPw = BCrypt.hashpw(registrationPayload.getPassword(), BCrypt.gensalt(10));
        userService.save(new User(registrationPayload.getUsername(), hashedPw, registrationPayload.getEmail()));

        return new ResponseEntity<>(new ApiResponse(null, "Create user successfully!"), HttpStatus.OK);
    }
}
