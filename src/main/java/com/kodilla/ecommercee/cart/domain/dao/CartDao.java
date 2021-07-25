package com.kodilla.ecommercee.cart.domain.dao;

import com.kodilla.ecommercee.cart.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface CartDao extends JpaRepository<Cart, Long> {
}
