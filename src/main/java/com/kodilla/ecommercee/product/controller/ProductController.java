package com.kodilla.ecommercee.product.controller;

import com.kodilla.ecommercee.product.domain.Product;
import com.kodilla.ecommercee.product.domain.ProductDto;
import com.kodilla.ecommercee.product.mapper.ProductMapper;
import com.kodilla.ecommercee.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductMapper productMapper;
    private final ProductService productService;

    @GetMapping(value = "getProducts")
    public List<ProductDto> getProducts() {
        List<Product> productList = productService.getAllProducts();
        return productMapper.mapToProduct(productList);
    }

    @GetMapping(value = "getProduct")
    public ProductDto getProduct (@RequestParam Long id) {
        return new ProductDto(1L,"Product","Description", 100,1L);
    }

    @PostMapping(value = "createProduct")
    public void createProduct(@RequestBody ProductDto productDto) {
    }

    @PutMapping(value ="updateProduct")
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        return new ProductDto(1L, "Updated Product", "Updated Description",90, 1L);
    }

    @DeleteMapping(value = "deleteProduct")
    public void deleteTask(@RequestBody Long id) {
    }
}
