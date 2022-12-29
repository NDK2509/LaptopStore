package com.example.LaptopStoreAPI.resposes;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductApiResponse extends ApiResponse<Object> {
    private int count;

    public ProductApiResponse(Object data, String message) {
        super(data, message);
        if (data instanceof List<?>) this.count = ((List<?>) data).size();
    }
}
