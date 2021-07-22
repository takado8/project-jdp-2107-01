package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.ProductDto;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RequestMapping("v1/cart")
@RestController
public class CartController {

    @GetMapping(path = "products")
    public List<ProductDto> getProducts() {
        ProductDto product1 = new ProductDto(1, "kurtka zimowa", "Pellentesque tempus interdum quam ut rhoncus. Donec ullamcorper turpis dolor. Donec euismod pretium eros et eleifend. Aliquam vulputate faucibus lorem non auctor. Vivamus erat turpis, molestie a nisl non, scelerisque luctus enim. Nunc mi mi, laoreet ac mollis nec, pharetra sit amet tortor. Vivamus a bibendum purus.", 100, 1);
        ProductDto product2 = new ProductDto(2, "płaszcz", "Pellentesque tempus interdum quam ut rhoncus. Donec ullamcorper turpis dolor. Donec euismod pretium eros et eleifend. Aliquam vulputate faucibus lorem non auctor. Vivamus erat turpis, molestie a nisl non, scelerisque luctus enim. Nunc mi mi, laoreet ac mollis nec, pharetra sit amet tortor. Vivamus a bibendum purus.", 150, 1);
        ProductDto product3 = new ProductDto(8, "krawat", "Pellentesque tempus interdum quam ut rhoncus. Donec ullamcorper turpis dolor. Donec euismod pretium eros et eleifend. Aliquam vulputate faucibus lorem non auctor. Vivamus erat turpis, molestie a nisl non, scelerisque luctus enim. Nunc mi mi, laoreet ac mollis nec, pharetra sit amet tortor. Vivamus a bibendum purus.", 50, 2);
        return Arrays.asList(product1, product2, product3);
    }

    @PostMapping(path = "createCart")
    public void createCart() {
    }

    @PostMapping(path = "add")
    public void addProducts(@RequestBody List<ProductDto> products) {
    }

    @DeleteMapping(path = "delete")
    public void deleteProduct(@RequestParam Integer id) {
    }

    @PostMapping(path = "createOrder")
    public void createOrder() {
    }

}
