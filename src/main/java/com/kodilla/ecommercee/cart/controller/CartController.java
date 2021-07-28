package com.kodilla.ecommercee.cart.controller;

import com.kodilla.ecommercee.cart.domain.CartDto;
import com.kodilla.ecommercee.product.domain.ProductDto;
import com.kodilla.ecommercee.order.domain.OrderDto;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RequestMapping("v1/cart")
@RestController
public class CartController {

    @GetMapping(path = "products")
    public List<ProductDto> getProducts(@RequestParam Long cartId) {
        ProductDto product1 = new ProductDto(1L, "kurtka zimowa", "Pellentesque tempus interdum quam ut rhoncus. Donec ullamcorper turpis dolor. Donec euismod pretium eros et eleifend. Aliquam vulputate faucibus lorem non auctor. Vivamus erat turpis, molestie a nisl non, scelerisque luctus enim. Nunc mi mi, laoreet ac mollis nec, pharetra sit amet tortor. Vivamus a bibendum purus.", 100, 1L);
        ProductDto product2 = new ProductDto(2L, "płaszcz", "Pellentesque tempus interdum quam ut rhoncus. Donec ullamcorper turpis dolor. Donec euismod pretium eros et eleifend. Aliquam vulputate faucibus lorem non auctor. Vivamus erat turpis, molestie a nisl non, scelerisque luctus enim. Nunc mi mi, laoreet ac mollis nec, pharetra sit amet tortor. Vivamus a bibendum purus.", 150, 1L);
        ProductDto product3 = new ProductDto(8L, "krawat", "Pellentesque tempus interdum quam ut rhoncus. Donec ullamcorper turpis dolor. Donec euismod pretium eros et eleifend. Aliquam vulputate faucibus lorem non auctor. Vivamus erat turpis, molestie a nisl non, scelerisque luctus enim. Nunc mi mi, laoreet ac mollis nec, pharetra sit amet tortor. Vivamus a bibendum purus.", 50, 2L);
        return Arrays.asList(product1, product2, product3);
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

}
