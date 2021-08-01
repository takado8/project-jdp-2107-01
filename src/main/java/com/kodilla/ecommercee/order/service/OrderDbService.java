package com.kodilla.ecommercee.order.service;

import com.kodilla.ecommercee.order.domain.Order;
import com.kodilla.ecommercee.order.repository.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDbService {

    private OrderDao orderDao;

    @Autowired
    public void setDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public List<Order> getAllOrders() {
        return orderDao.findAll();
    }

    public Order saveOrder(final Order order) {
        return orderDao.save(order);
    }

    public Optional<Order> getOrder(final Long orderId) {
        return orderDao.findById(orderId);
    }

    public void deleteOrder(final Long orderId) {
        orderDao.deleteById(orderId);
    }
}
