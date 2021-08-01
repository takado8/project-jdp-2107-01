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


    @GetMapping(value = "getProducts")
    public List<ProductDto> getProducts() {
        return productMapper.mapToProductDtoList(
                productDbService.getAllProducts()
        );
    }

    @GetMapping(value = "getProduct")

    public ProductDto getProduct(@RequestParam Long productId) {
        return productMapper.mapToProductDto(
                productDbService.getProduct(productId)
        );
    }

    @PostMapping(value = "createProduct")
    public void createProduct(@RequestBody ProductDto productDto) {
        productDbService.saveProduct(
                productMapper.mapToProduct(productDto)
        );
    }

    @PutMapping(value = "updateProduct")
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        return productMapper.mapToProductDto(
                productDbService.saveProduct(
                        productMapper.mapToProduct(productDto)
                )
        );
    }

    @DeleteMapping(value = "deleteProduct")
    public void deleteProduct(@RequestParam Long productId) {
        productDbService.deleteProduct(productId);
    }
}
