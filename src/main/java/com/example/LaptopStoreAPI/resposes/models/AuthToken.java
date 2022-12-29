package com.example.LaptopStoreAPI.resposes.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthToken {
    private String accessToken;
    private String refreshToken;
}
