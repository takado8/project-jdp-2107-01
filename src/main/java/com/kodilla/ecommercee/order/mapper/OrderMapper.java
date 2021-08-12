package com.kodilla.ecommercee.order.mapper;

import com.kodilla.ecommercee.order.domain.Order;
import com.kodilla.ecommercee.order.domain.OrderDto;
import com.kodilla.ecommercee.product.domain.Product;
import com.kodilla.ecommercee.product.repository.ProductDao;
import com.kodilla.ecommercee.user.repository.UserDao;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
public class OrderMapper {
    private final UserDao userDao;
    private final ProductDao productDao;

    public OrderDto mapOrderToDto(final Order order) {
        return new OrderDto(
                order.getId(),
                order.getDateOfOrder(),
                order.getPrice(),
                order.getProducts().stream().map(Product::getId).collect(Collectors.toList()),
                order.getUser().getId()
        );
    }

    public Order mapDtoToOrder(final OrderDto orderDto) {
        return new Order(
                orderDto.getDateOfOrder(),
                orderDto.getPrice(),
                productDao.findAllById(orderDto.getProductsIds()),
                userDao.findById(orderDto.getUserId()).orElseThrow(() ->
                        new RuntimeException("User of id '" + orderDto.getUserId() + "' not found"))
        );
    }

    public List<OrderDto> mapDtoToOrderList(List<Order> orderList) {
        return orderList.stream()
                .map(this::mapOrderToDto)
                .collect(Collectors.toList());
    }
}
