package com.kodilla.ecommercee.cart.service;

import com.kodilla.ecommercee.cart.domain.Cart;
import com.kodilla.ecommercee.cart.repository.CartDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    private CartDao cartDao;

    @Autowired
    public void setCartDao(CartDao cartDao) {
        this.cartDao = cartDao;
    }

    public Cart getCart(Long cartId) {
        return cartDao.getOne(cartId);
    }
}
