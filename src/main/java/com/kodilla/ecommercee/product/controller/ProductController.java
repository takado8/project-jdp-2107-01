package com.kodilla.ecommercee.product.controller;

import com.kodilla.ecommercee.product.domain.ProductDto;
import com.kodilla.ecommercee.product.mapper.ProductMapper;
import com.kodilla.ecommercee.product.service.ProductDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/product")
public class ProductController {

    private ProductDbService productDbService;

    @Autowired
    public void setProductService(ProductDbService productDbService) {
        this.productDbService = productDbService;
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
    public ProductDto getProduct(@RequestParam Long id) throws ProductNotFoundException {
        return productMapper.mapToProductDto(
                productDbService.getProduct(id).orElseThrow(ProductNotFoundException::new)
        );
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
