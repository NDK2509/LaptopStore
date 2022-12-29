package com.example.LaptopStoreAPI.models;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Document(collection = "Users")
@NoArgsConstructor
public class User {
    @Id
    private String userId;
    @NotNull(message = "Username is required!")
    @NotEmpty(message = "Username is required!")
    private String username;
    @NotNull(message = "Password is required!")
    @NotEmpty(message = "Password is required!")
    @Size(min = 4, message = "Password must has more than or equals 4 characters!")
    private String password;
    @NotEmpty(message = "Password is required!")
    @Email(message = "Invalid email!")
    private String email;

    public User(String username, String password, String email) {
        this.userId = UUID.randomUUID().toString();
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
