package com.kodilla.ecommercee.product.controller;

import com.kodilla.ecommercee.product.domain.ProductDto;
import com.kodilla.ecommercee.product.mapper.ProductMapper;
import com.kodilla.ecommercee.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/product")
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    private ProductMapper productMapper;

    @Autowired
    public void setProductMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @GetMapping(value = "getProducts")
    public List<ProductDto> getProducts() {
        return new ArrayList<>();
    }

    @GetMapping(value = "getProduct")
    public ProductDto getProduct(@RequestParam Long id) {
        return productMapper.mapToProductDto(productService.getProduct(id));
    }

    @PostMapping(value = "createProduct")
    public void createProduct(@RequestBody ProductDto productDto) {
    }

    @PutMapping(value = "updateProduct")
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        return null;
    }

    @DeleteMapping(value = "deleteProduct")
    public void deleteTask(@RequestBody Long id) {
    }
}
