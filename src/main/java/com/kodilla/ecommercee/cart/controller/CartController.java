package com.kodilla.ecommercee.cart.controller;

import com.kodilla.ecommercee.cart.domain.CartDto;
import com.kodilla.ecommercee.cart.mapper.CartMapper;
import com.kodilla.ecommercee.cart.service.CartDbService;
import com.kodilla.ecommercee.order.domain.OrderDto;
import com.kodilla.ecommercee.product.domain.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("v1/cart")
@RestController
public class CartController {

    private CartDbService cartDbService;

    @Autowired
    public void setCartService(CartDbService cartDbService) {
        this.cartDbService = cartDbService;
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
    public CartDto getCart(@RequestParam Long cartId) throws CartNotFoundException {
        return cartMapper.mapToCartDto(
                cartDbService.getCart(cartId).orElseThrow(CartNotFoundException::new)
        );
    }
}
