package com.kodilla.ecommercee.product.mapper;

import com.kodilla.ecommercee.cart.domain.Cart;
import com.kodilla.ecommercee.order.domain.Order;
import com.kodilla.ecommercee.product.domain.Product;
import com.kodilla.ecommercee.product.domain.ProductDto;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ProductMapper {

    public ProductDto mapToProductDto(Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getGroupId().getId(),
                product.getOrders().stream()
                        .map(Order::getId)
                        .collect(Collectors.toList()),
                product.getCarts().stream()
                        .map(Cart::getId)
                        .collect(Collectors.toList())
        );
    }
}
