package com.example.LaptopStoreAPI.utils;

import com.example.LaptopStoreAPI.models.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Base64;

public class Base64Utils {
    public static <T> T decode(String base64Str, Class<T> outputClass){
        var json = Base64.getDecoder().decode(base64Str).toString();
        var mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, outputClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
