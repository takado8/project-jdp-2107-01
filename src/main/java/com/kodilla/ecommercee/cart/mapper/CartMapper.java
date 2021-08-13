package com.kodilla.ecommercee.cart.mapper;

import com.kodilla.ecommercee.cart.domain.Cart;
import com.kodilla.ecommercee.cart.domain.CartDto;
import com.kodilla.ecommercee.product.domain.Product;
import com.kodilla.ecommercee.product.repository.ProductDao;
import com.kodilla.ecommercee.user.repository.UserDao;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
public class CartMapper {
    private final UserDao userDao;
    private final ProductDao productDao;

    public CartDto mapCartToDto(final Cart cart) {
        return new CartDto(cart.getId(),
                cart.getUser().getId(),
                cart.getName(),
                cart.getDescription(),
                cart.getPrice(),
                cart.getProducts()
                        .stream()
                        .map(Product::getId)
                        .collect(Collectors.toList()));
    }

    public Cart mapDtoToCart(final CartDto cartDto) {
        return new Cart(
                cartDto.getName(),
                cartDto.getDescription(),
                cartDto.getPrice(),
                userDao.findById(cartDto.getUserId()).orElseThrow(() ->
                        new RuntimeException("User of id '" + cartDto.getUserId() + "' not found")),
                productDao.findAllById(cartDto.getProductsIds())
        );
    }

}
