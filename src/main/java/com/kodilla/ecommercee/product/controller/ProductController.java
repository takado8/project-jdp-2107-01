package com.kodilla.ecommercee.product.controller;

import com.kodilla.ecommercee.product.domain.ProductDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/product")
public class ProductController {

    @GetMapping(value = "getProducts")
    public List<ProductDto> getProducts() {
        return new ArrayList<>();
    }

    @GetMapping(value = "getProduct")
    public ProductDto getProduct (@RequestParam Long id) {
        return null;
    }

    @PostMapping(value = "createProduct")
    public void createProduct(@RequestBody ProductDto productDto) {
    }

    @PutMapping(value ="updateProduct")
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        return null;
    }

    @DeleteMapping(value = "deleteProduct")
    public void deleteTask(@RequestParam Long id) {
    }
}