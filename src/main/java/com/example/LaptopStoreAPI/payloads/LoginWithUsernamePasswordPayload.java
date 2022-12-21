package com.example.LaptopStoreAPI.payloads;

import lombok.Data;

@Data
public class LoginWithUsernamePasswordPayload extends AuthPayload {
    private String username;
    private String password;
}
