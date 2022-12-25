package com.example.LaptopStoreAPI.controllers;

import com.example.LaptopStoreAPI.models.Product;
import com.example.LaptopStoreAPI.resposes.ApiResponse;
import com.example.LaptopStoreAPI.resposes.ProductApiResponse;
import com.example.LaptopStoreAPI.services.IProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/products")
@CrossOrigin("*")
@AllArgsConstructor
public class ProductApiController implements ApiController<ApiResponse, Product> {
    protected IProductService productService;

    @Override
    @GetMapping("")
    public ResponseEntity<ApiResponse> getAll(HttpServletRequest request) {

        var productList = productService.findAll();
        if (productList.size() == 0) {
            return new ResponseEntity<>(new ApiResponse<>(null, "No data!"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ApiResponse<>(productList, "Get data successfully!"), HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getOneById(HttpServletRequest request, @PathVariable("id") String id) {
        var product = productService.findById(id);
        if (product == null) {
            return new ResponseEntity<>(new ProductApiResponse(null, "Product is not found!"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ProductApiResponse(product, "Get product successfully!"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiResponse> save(HttpServletRequest request, Product model) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse> delete(HttpServletRequest request, String id) {
        return null;
    }


}
