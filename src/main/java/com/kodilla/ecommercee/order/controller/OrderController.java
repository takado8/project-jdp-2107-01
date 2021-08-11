package com.kodilla.ecommercee.order.controller;

import com.kodilla.ecommercee.order.domain.Order;
import com.kodilla.ecommercee.order.domain.OrderDto;
import com.kodilla.ecommercee.order.mapper.OrderMapper;
import com.kodilla.ecommercee.order.service.OrderDbService;
import com.kodilla.ecommercee.product.domain.ProductDto;
import com.kodilla.ecommercee.product.mapper.ProductMapper;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/orders")
@Data
public class OrderController {
    private final OrderDbService orderDbService;
    private final ProductMapper productMapper;
    private final OrderMapper orderMapper;

    @GetMapping("getOrders")
    public List<OrderDto> getAllOrders() {
        List<Order> orderList = orderDbService.getOrders();
        return orderMapper.mapDtoToOrderList(orderList);
    }

    @GetMapping("getOrder")
    public OrderDto getOrder(@RequestParam Long orderId) {
        Order order = orderDbService.getOrder(orderId);
        return orderMapper.mapOrderToDto(order);
    }

    @PostMapping("createOrder")
    public void createOrder(@RequestBody OrderDto orderDto) {
        orderDbService.createOrder(orderMapper.mapDtoToOrder(orderDto));
    }

    @PutMapping("updateOrder")
    public OrderDto updateOrder(@RequestBody OrderDto orderDto) {
        Order order = orderMapper.mapDtoToOrder(orderDto);
        Order newOrder = orderDbService.createOrder(order);
        return orderMapper.mapOrderToDto(newOrder);
    }

    @DeleteMapping("deleteOrder")
    public void deleteOrder(@RequestParam Long orderId) {
        orderDbService.deleteOrderById(orderId);
    }

    @GetMapping(path = "getProducts")
    public List<ProductDto> getProductsFromOrder(@RequestParam Long orderId) {
        OrderDto orderDto = getOrder(orderId);
        Order order = orderMapper.mapDtoToOrder(orderDto);
        return productMapper.mapToProductDtoList(order.getProducts());
    }
}
