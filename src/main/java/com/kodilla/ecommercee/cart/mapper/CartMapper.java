package com.kodilla.ecommercee.cart.mapper;

import com.kodilla.ecommercee.cart.domain.Cart;
import com.kodilla.ecommercee.cart.domain.CartDto;
import com.kodilla.ecommercee.product.controller.ProductController;
import com.kodilla.ecommercee.product.domain.Product;
import com.kodilla.ecommercee.product.mapper.ProductMapper;
import com.kodilla.ecommercee.product.repository.ProductDao;
import com.kodilla.ecommercee.user.UserMapper;
import com.kodilla.ecommercee.user.controller.UserController;
import com.kodilla.ecommercee.user.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CartMapper {

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    private ProductDao productDao;

    @Autowired
    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    public Cart mapToCart(CartDto cartDto) {
        return new Cart(
                cartDto.getId(),
                cartDto.getName(),
                cartDto.getDescription(),
                cartDto.getPrice(),
                userDao.getOne(cartDto.getUserId()),
                cartDto.getProductsId().stream()
                        .map(productDao::getOne)
                        .collect(Collectors.toList())
        );
    }

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
