package com.kodilla.ecommercee.order.service;

import com.kodilla.ecommercee.order.controller.OrderNotFoundException;
import com.kodilla.ecommercee.order.domain.Order;
import com.kodilla.ecommercee.order.repository.OrderDao;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class OrderDbService {
    private final OrderDao orderDao;

    public Order createOrder(Order order) {
        return orderDao.save(order);
    }

    public List<Order> getOrders() {
        return orderDao.findAll();
    }

    public Order getOrder(Long orderId) {
        return orderDao.findById(orderId).orElseThrow(() -> new OrderNotFoundException("Order with id: " + orderId + " not found"));
    }

    public void deleteOrderById(Long orderId) {
        orderDao.deleteById(orderId);
    }
}
