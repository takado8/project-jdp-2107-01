package com.kodilla.ecommercee.order.mapper;

import com.kodilla.ecommercee.order.domain.Order;
import com.kodilla.ecommercee.order.domain.OrderDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderMapper {

    public Order mapToOrder(final OrderDto orderDto) {
        return new Order(
                orderDto.getId(),
                orderDto.getPrice(),
                orderDto.getDateOfOrder(),
                orderDto.getUserId(),
                orderDto.getProducts()
        );
    }

    public OrderDto mapToOrderDto(final Order order) {
        return new OrderDto(
                order.getId(),
                order.getPrice(),
                order.getDateOfOrder(),
                order.getUserId(),
                order.getProducts()
        );
    }

    public List<OrderDto> mapToOrderDtoList(final List<Order> orders) {
        return orders.stream()
                .map(this::mapToOrderDto)
                .collect(Collectors.toList());
    }
}
