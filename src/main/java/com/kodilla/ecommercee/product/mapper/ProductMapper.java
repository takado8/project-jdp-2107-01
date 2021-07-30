package com.kodilla.ecommercee.product.mapper;

import com.kodilla.ecommercee.exceptions.OrderNotFoundException;
import com.kodilla.ecommercee.group.repository.GroupDao;
import com.kodilla.ecommercee.product.domain.Product;
import com.kodilla.ecommercee.product.domain.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductMapper {

    private final GroupDao groupDao;

    @Autowired
    public ProductMapper(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    public Product mapToProduct(final ProductDto productDto) throws OrderNotFoundException {
        return new Product(
                productDto.getId(),
                productDto.getName(),
                productDto.getDescription(),
                productDto.getPrice(),
                groupDao.findById(productDto.getGroupId()).orElseThrow(OrderNotFoundException::new)
        );
    }

    public ProductDto mapToProductDto(final Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getId()
        );
    }

    public List<ProductDto> mapToProductDtoList(final List<Product> productList) {
        return productList.stream()
                .map(this::mapToProductDto)
                .collect(Collectors.toList());
    }
}