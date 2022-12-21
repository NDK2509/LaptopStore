package com.example.LaptopStoreAPI.resposes;

import lombok.Data;

@Data
public class ApiResponse<D> {
    private D data;
    private String message;
    public ApiResponse(D data, String message) {
        setData(data);
        setMessage(message);
    }
}
