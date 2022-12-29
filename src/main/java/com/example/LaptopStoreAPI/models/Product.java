package com.example.LaptopStoreAPI.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

@Data
@Document(collection = "Products")
public class Product {
    @Id
    private String id;
    @NotEmpty(message = "Name is required!")
    @NotNull(message = "Name is required!")
    private String name;
    @NotNull(message = "Price is required!")
    @Positive(message = "Price must be greater than 0!")
    private float price;
    @NotNull(message = "Created date is required!")
    private long createdDate;

    public Product(String name, float price) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.price = price;
        this.createdDate = new Date().getTime();
    }
}
