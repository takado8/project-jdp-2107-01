package com.kodilla.ecommercee.cart;

import com.kodilla.ecommercee.cart.domain.Cart;
import com.kodilla.ecommercee.cart.repository.CartDao;
import com.kodilla.ecommercee.product.domain.Product;
import com.kodilla.ecommercee.product.repository.ProductDao;
import com.kodilla.ecommercee.user.domain.User;
import com.kodilla.ecommercee.user.repository.UserDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CartTestSuite {
    @Autowired
    private CartDao cartDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private UserDao userDao;

    @Test
    public void testCartSave() {
        //Given
        User user = new User();
        Product product = new Product("kurtka", 178.99);
        Cart cart = new Cart("cartName", "cartDescription", 120.50, user);
        cart.getProducts().add(product);
        userDao.save(user);
        productDao.save(product);
        cartDao.save(cart);
        long id = cart.getId();

        //When
        Optional<Cart> savedCart = cartDao.findById(id);

        //Then
        Assert.assertTrue(savedCart.isPresent());

        //Clean up
        cartDao.deleteById(cart.getId());
        userDao.deleteById(user.getId());
        productDao.deleteById(product.getId());
    }

    @Test
    public void testProductCartRelation() {
        //Given
        User user = new User();
        Product product = new Product("kurtka", 178.99);
        Cart cart = new Cart("cartName", "cartDescription", 120.50, user);
        cart.getProducts().add(product);
        product.getCarts().add(cart);
        userDao.save(user);
        productDao.save(product);
        cartDao.save(cart);
        long id = cart.getId();

        //When
        Optional<Cart> optionalCart = cartDao.findById(id);
        Cart savedCart = optionalCart.orElseThrow(() -> new RuntimeException("No Cart"));
        String productName = savedCart.getProducts().get(0).getName();

        //Then
        Assert.assertEquals("kurtka", productName);

        //Clean up
        cartDao.deleteById(cart.getId());
        userDao.deleteById(user.getId());
        productDao.deleteById(product.getId());
    }

    @Test
    public void testUserCartRelation() {
        //Given
        User user = new User();
        Product product = new Product("kurtka", 178.99);
        Cart cart = new Cart("cartName", "cartDescription", 120.50, user);
        cart.getProducts().add(product);
        product.getCarts().add(cart);
        user.setUsername("Pawel");
        userDao.save(user);
        productDao.save(product);
        cartDao.save(cart);
        long id = cart.getId();

        //When
        Optional<Cart> optionalCart = cartDao.findById(id);
        Cart savedCart = optionalCart.orElseThrow(() -> new RuntimeException("No Cart"));

        //Then
        Assert.assertEquals(user,savedCart.getUserID());
        Assert.assertEquals("Pawel", savedCart.getUserID().getUsername());

        //Clean up
        cartDao.deleteById(cart.getId());
        userDao.deleteById(user.getId());
        productDao.deleteById(product.getId());
    }

    @Test
    public void testCartUpdate() {
        //Given
        User user = new User();
        Product product = new Product("kurtka", 178.99);
        Cart cart = new Cart("cartName", "cartDescription", 120.50, user);
        cart.getProducts().add(product);
        product.getCarts().add(cart);
        userDao.save(user);
        productDao.save(product);
        cartDao.save(cart);
        long id = cart.getId();

        //When
        Optional<Cart> optionalSavedCart = cartDao.findById(id);
        Cart savedCart = optionalSavedCart.orElseThrow(() -> new RuntimeException("No Cart"));
        savedCart.setName("changedCart");
        cartDao.save(savedCart);

        Optional<Cart> optionalChangedCart = cartDao.findById(id);
        Cart changedCart = optionalChangedCart.orElseThrow(() -> new RuntimeException("No Cart"));

        //Then
        Assert.assertEquals("changedCart", changedCart.getName());

        //Clean up
        cartDao.deleteById(cart.getId());
        userDao.deleteById(user.getId());
        productDao.deleteById(product.getId());
    }

    @Test
    public void testCartDelete() {
        //Given
        User user = new User();
        Product product = new Product("kurtka", 178.99);
        Cart cart = new Cart("cartName", "cartDescription", 120.50, user);
        cart.getProducts().add(product);
        product.getCarts().add(cart);
        userDao.save(user);
        productDao.save(product);
        cartDao.save(cart);
        long id = cart.getId();

        //When
        Optional<Cart> optionalCart = cartDao.findById(id);
        Cart savedCart = optionalCart.orElseThrow(() -> new RuntimeException("No Cart"));
        long savedCartId = savedCart.getId();
        cartDao.deleteById(savedCartId);

        //Then
        Assert.assertFalse(cartDao.findById(savedCartId).isPresent());

        //Clean up
        userDao.deleteById(user.getId());
        productDao.deleteById(product.getId());
    }
}
