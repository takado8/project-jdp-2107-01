package com.kodilla.ecommercee.order.service;

import com.kodilla.ecommercee.order.domain.Order;
import com.kodilla.ecommercee.order.repository.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDbService {

    private OrderDao dao;

    @Autowired
    public void setDao(OrderDao dao) {
        this.dao = dao;
    }

    public List<Order> getAllOrders() {
        return dao.findAll();
    }

    public Order saveOrder(final Order order) {
        return dao.save(order);
    }

    public Order getOrder(final Long orderId) {
        return dao.getOne(orderId);
    }

    public void deleteOrder(final Long orderId) {
        dao.deleteById(orderId);
    }
}
