package com.example.LaptopStoreAPI.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("SecretKeys")
@AllArgsConstructor
public class SecretKey {
    @Id
    private String userId;
    private String refreshSecret;
}
