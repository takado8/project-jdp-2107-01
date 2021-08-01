package com.kodilla.ecommercee.product.controller;

import com.kodilla.ecommercee.product.domain.ProductDto;
import com.kodilla.ecommercee.product.mapper.ProductMapper;
import com.kodilla.ecommercee.product.service.ProductDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
