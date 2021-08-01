package com.kodilla.ecommercee.cart.controller;

import com.kodilla.ecommercee.cart.domain.CartDto;
import com.kodilla.ecommercee.order.domain.OrderDto;
import com.kodilla.ecommercee.product.domain.ProductDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("v1/cart")
@RestController
public class CartController {

    @GetMapping(path = "products")
    public List<ProductDto> getProducts(@RequestParam Long cartId) {
        return new ArrayList<>();
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
        return new OrderDto(1L, 1L, 100);
    }

}
