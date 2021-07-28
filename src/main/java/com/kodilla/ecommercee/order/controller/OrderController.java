package com.kodilla.ecommercee.order.controller;

import com.kodilla.ecommercee.order.domain.OrderDto;
import com.kodilla.ecommercee.order.mapper.OrderMapper;
import com.kodilla.ecommercee.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/orders")
public class OrderController {

    private final OrderService service;
    private final OrderMapper mapper;

    @GetMapping("getOrders")
    public List<OrderDto> getAllOrders() {
        return mapper.mapToOrderDtoList(service.getAllOrders());
    }

    @PostMapping("createOrder")
    public void createOrder(@RequestBody OrderDto orderDto) {
        service.saveOrder(mapper.mapToOrder(orderDto));
    }

    @GetMapping("getOrder")
    public OrderDto getOrder(@RequestParam Long orderId) {
        return mapper.mapToOrderDto(service.getOrder(orderId));
    }

    @PutMapping("updateOrder")
    public OrderDto updateOrder(@RequestBody OrderDto orderDto) {
        return mapper.mapToOrderDto(service.saveOrder(mapper.mapToOrder(orderDto)));
    }

    @DeleteMapping("deleteOrder")
    public void deleteOrder(@RequestParam Long orderId) {
        service.deleteOrder(orderId);
    }
}
