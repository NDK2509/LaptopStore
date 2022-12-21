package com.example.LaptopStoreAPI.models;

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
    private String username;
    private String password;
    private String email;

    public User(String username, String password, String email) {
        this.userId = UUID.randomUUID().toString();
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
