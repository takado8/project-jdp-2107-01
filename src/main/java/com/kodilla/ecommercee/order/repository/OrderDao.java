package com.kodilla.ecommercee.order.repository;

import com.kodilla.ecommercee.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional()
@Repository
public interface OrderDao extends JpaRepository<Order, Long> {
}
