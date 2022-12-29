package com.example.LaptopStoreAPI.controllers;

import com.example.LaptopStoreAPI.models.Product;
import com.example.LaptopStoreAPI.resposes.ApiResponse;
import com.example.LaptopStoreAPI.resposes.ProductApiResponse;
import com.example.LaptopStoreAPI.services.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/products")
@CrossOrigin("*")
@AllArgsConstructor
public class ProductApiController implements ApiController<ApiResponse<?>, Product> {
    protected IProductService productService;

    @Override
    @GetMapping("")
    public ResponseEntity<ApiResponse<?>> getAll() {

        var productList = productService.findAll();
        if (productList.size() == 0) {
            return new ResponseEntity<>(new ApiResponse<>(null, "No data!"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ProductApiResponse(productList, "Get data successfully!"), HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> getById(@PathVariable("id") String id) {
        var product = productService.findById(id);
        if (product == null) {
            return new ResponseEntity<>(new ApiResponse<>(null, "Product is not found!"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ApiResponse<>(product, "Get product successfully!"), HttpStatus.OK);
    }

    @Override
    @PostMapping("")
    public ResponseEntity<ApiResponse<?>> create(@RequestBody Product product) {
        var createdProduct = productService.create(product);
        return new ResponseEntity<>(new ApiResponse<>(createdProduct, "Create new product successfully!"), HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> update(@PathVariable("id") String id, Product product) {
        if (productService.exitsById(id)) {
            product.setId(id);
            var updatedProduct = productService.update(product);
            return new ResponseEntity<>(new ApiResponse<>(updatedProduct, "Update successfully!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse<>(null, "Product not exists!"), HttpStatus.NOT_FOUND);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> delete(@PathVariable("id") String id) {
        if (productService.exitsById(id)) {
            var updatedProduct = productService.delete(id);
            return new ResponseEntity<>(new ApiResponse<>(updatedProduct, "Delete successfully!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse<>(null, "Product not exists!"), HttpStatus.NOT_FOUND);
    }
}
