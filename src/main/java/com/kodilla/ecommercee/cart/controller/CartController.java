package com.kodilla.ecommercee.cart.controller;

import com.kodilla.ecommercee.cart.domain.Cart;
import com.kodilla.ecommercee.cart.domain.CartDto;
import com.kodilla.ecommercee.cart.mapper.CartMapper;
import com.kodilla.ecommercee.cart.repository.CartDao;
import com.kodilla.ecommercee.cart.service.CartService;
import com.kodilla.ecommercee.group.domain.Group;
import com.kodilla.ecommercee.group.repository.GroupDao;
import com.kodilla.ecommercee.product.domain.ProductDto;
import com.kodilla.ecommercee.order.domain.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RequestMapping("v1/cart")
@RestController
public class CartController {

    private CartService cartService;

    @Autowired
    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }

    private CartMapper cartMapper;

    @Autowired
    public void setCartMapper(CartMapper cartMapper) {
        this.cartMapper = cartMapper;
    }

    @GetMapping(path = "products")
    public List<ProductDto> getProducts(@RequestParam Long cartId) {
        return null;
    }

    @PostMapping(path = "createCart")
    public void createCart(@RequestBody CartDto cartDto) {
    }

    @PostMapping(path = "add")
    public void addProduct(@RequestParam Long productId, @RequestParam Long cartId) {
    }

    @DeleteMapping(path = "delete")
    public void deleteProduct(@RequestParam Long productId, @RequestParam Long cartId) {
    }

    @PostMapping(path = "createOrder")
    public OrderDto createOrder(@RequestParam Long cartId) {
        return null;
    }

    @GetMapping(path = "getCart")
    public CartDto getCart(@RequestParam Long cartId) {
        return cartMapper.mapToCartDto(cartService.getCart(cartId));
    }
}
