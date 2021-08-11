package com.kodilla.ecommercee.product.controller;

import com.kodilla.ecommercee.product.domain.ProductDto;
import com.kodilla.ecommercee.product.mapper.ProductMapper;
import com.kodilla.ecommercee.product.service.ProductDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductDbService productDbService;
    private final ProductMapper productMapper;

    @GetMapping("getProducts")
    public List<ProductDto> getProducts() {
        return productMapper.mapToProductDtoList(
                productDbService.getAllProducts()
        );
    }

    @GetMapping("getProduct")

    public ProductDto getProduct(@RequestParam Long productId) {
        return productMapper.mapToProductDto(
                productDbService.getProduct(productId)
        );
    }

    @PostMapping("createProduct")
    public void createProduct(@RequestBody ProductDto productDto) {
        productDbService.saveProduct(
                productMapper.mapToProduct(productDto)
        );
    }

    @PutMapping("updateProduct")
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        return productMapper.mapToProductDto(
                productDbService.saveProduct(
                        productMapper.mapToProduct(productDto)
                )
        );
    }

    @DeleteMapping("deleteProduct")
    public void deleteProduct(@RequestParam Long productId) {
        productDbService.deleteProduct(productId);
    }
}
