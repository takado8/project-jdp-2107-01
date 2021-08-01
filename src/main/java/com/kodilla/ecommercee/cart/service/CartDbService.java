package com.kodilla.ecommercee.cart.service;

import com.kodilla.ecommercee.cart.domain.Cart;
import com.kodilla.ecommercee.cart.repository.CartDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartDbService {

    private CartDao cartDao;

    @Autowired
    public void setCartDao(CartDao cartDao) {
        this.cartDao = cartDao;
    }

    public Optional<Cart> getCart(Long cartId) {
        return cartDao.findById(cartId);
    }
}
