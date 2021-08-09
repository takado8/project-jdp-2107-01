package com.kodilla.ecommercee.cart.service;

import com.kodilla.ecommercee.cart.domain.Cart;
import com.kodilla.ecommercee.cart.repository.CartDao;
import com.kodilla.ecommercee.order.domain.Order;
import com.kodilla.ecommercee.order.repository.OrderDao;
import com.kodilla.ecommercee.product.domain.Product;
import com.kodilla.ecommercee.product.repository.ProductDao;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Data

public class CartDbService {
    private final CartDao cartDao;
    private final ProductDao productDao;
    private final OrderDao orderDao;

    public Optional<Cart> getCart(Long cartId){
        return cartDao.findById(cartId);
    }

    public void createCart(Cart cart){
        cartDao.save(cart);
    }

    public void addProducts(List<Long> productsIds, Cart cart) {
        List<Product> products = productDao.findAllById(productsIds);
        cart.getProducts().addAll(products);
    }

    public Optional<Product> getProductToDelete(Long productId) {
        return productDao.findById(productId);
    }

    public void createOrder(Order order) {
        orderDao.save(order);
    }
}
