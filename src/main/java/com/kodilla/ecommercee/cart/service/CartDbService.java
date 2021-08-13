package com.kodilla.ecommercee.cart.service;

import com.kodilla.ecommercee.cart.controller.CartNotFoundException;
import com.kodilla.ecommercee.cart.domain.Cart;
import com.kodilla.ecommercee.cart.repository.CartDao;
import com.kodilla.ecommercee.order.domain.Order;
import com.kodilla.ecommercee.order.repository.OrderDao;
import com.kodilla.ecommercee.product.controller.ProductNotFoundException;
import com.kodilla.ecommercee.product.domain.Product;
import com.kodilla.ecommercee.product.repository.ProductDao;
import com.kodilla.ecommercee.user.domain.User;
import com.kodilla.ecommercee.user.repository.UserDao;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Service
@Data

public class CartDbService {
    private final CartDao cartDao;
    private final ProductDao productDao;
    private final OrderDao orderDao;
    private final UserDao userDao;

    public Optional<Cart> getCart(Long cartId){
        return cartDao.findById(cartId);
    }

    public Cart createCart(Cart cart){
        Cart created = cartDao.save(cart);
        User user = created.getUser();
        user.setCart(created);
        userDao.save(user);
        return created;
    }

    public void addProducts(List<Long> productsIds, Cart cart) {
        List<Product> products = productDao.findAllById(productsIds);
        cart.getProducts().addAll(products);
        BigDecimal price = calculateCartPrice(cart.getProducts());
        cart.setPrice(price);
        cartDao.save(cart);
    }

    public Optional<Product> getProductToDelete(Long productId) {
        return productDao.findById(productId);
    }

    public void removeProductFromCart(Long cartId, Long productId) {
        Cart cart = cartDao.findById(cartId).orElseThrow(() -> new CartNotFoundException("Missing cart with id " + cartId));
        Product toRemove = productDao.findById(productId).orElseThrow(() -> new ProductNotFoundException("Missing produc with id " + productId));
        cart.getProducts().remove(toRemove);
        cartDao.save(cart);
    }

    public Order createOrder(Order order) {
        return orderDao.save(order);
    }

    private BigDecimal calculateCartPrice(List<Product> products) {
        BigDecimal price = BigDecimal.ZERO;
        if (products == null) {
            return price;
        } else {
            for (Product product : products) {
                price = price.add(BigDecimal.valueOf(product.getPrice()));
            }
        }
        return price;
    }
}
