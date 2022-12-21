package com.example.LaptopStoreAPI.payloads;

import lombok.Data;

@Data
public class RegistrationPayload {
    private String username;
    private String email;
    private String password;
}
