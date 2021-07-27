package com.kodilla.ecommercee.order.controller;

import com.kodilla.ecommercee.order.domain.OrderDto;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/v1/orders")
@CrossOrigin(origins = "*")
@NoArgsConstructor
public class OrderController {

    @GetMapping("getOrders")
    public List<OrderDto> getOrders() {
        return Arrays.asList(
                new OrderDto(1L, 1L, 50),
                new OrderDto(2L, 2L, 80));
    }

    @GetMapping("getOrder")
    public OrderDto getOrder(@RequestParam Long orderId) {
        return new OrderDto(1L, 1L, 50);
    }

    @PostMapping("createOrder")
    public void createOrder(@RequestBody OrderDto orderDto) {
        System.out.println("order " + orderDto + " created");
    }

    @PutMapping("updateOrder")
    public OrderDto updateOrder(@RequestBody OrderDto orderDto) {
        return new OrderDto(1L, 1L, 50);
    }

    @DeleteMapping("deleteOrder")
    public void deleteOrder(@RequestParam Long orderId) {
        System.out.println("order " + orderId + " deleted");
    }
}
