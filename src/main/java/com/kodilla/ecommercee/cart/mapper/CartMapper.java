package com.kodilla.ecommercee.cart.mapper;

import com.kodilla.ecommercee.cart.domain.Cart;
import com.kodilla.ecommercee.cart.domain.CartDto;
import com.kodilla.ecommercee.product.domain.Product;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CartMapper {

    public CartDto mapToCartDto(Cart cart) {
        return new CartDto(
                cart.getId(),
                cart.getName(),
                cart.getDescription(),
                cart.getPrice(),
                cart.getUserID().getId(),
                cart.getProducts().stream()
                        .map(Product::getId)
                        .collect(Collectors.toList())
        );
    }
}
