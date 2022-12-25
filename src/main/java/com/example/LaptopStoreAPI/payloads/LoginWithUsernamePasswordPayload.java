package com.example.LaptopStoreAPI.payloads;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginWithUsernamePasswordPayload extends AuthPayload {
    private String username;
    private String password;
}
