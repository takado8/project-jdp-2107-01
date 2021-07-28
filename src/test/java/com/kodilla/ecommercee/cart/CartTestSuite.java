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
        cart.getProductList().add(product);

        //When
        userDao.save(user);
        productDao.save(product);
        cartDao.save(cart);

        long id = cart.getId();
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
        cart.getProductList().add(product);
        product.getCarts().add(cart);

        //When
        userDao.save(user);
        productDao.save(product);
        cartDao.save(cart);

        long id = cart.getId();
        Optional<Cart> optionalCart = cartDao.findById(id);
        Cart savedCart = optionalCart.orElseThrow(() -> new RuntimeException("No Cart"));
        String productName = savedCart.getProductList().get(0).getName();

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
        cart.getProductList().add(product);
        product.getCarts().add(cart);

        //When
        userDao.save(user);
        productDao.save(product);
        cartDao.save(cart);
        long id = cart.getId();
        Optional<Cart> optionalCart = cartDao.findById(id);
        Cart savedCart = optionalCart.orElseThrow(() -> new RuntimeException("No Cart"));


        //Then
        Assert.assertEquals(user,savedCart.getUser());

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
        cart.getProductList().add(product);
        product.getCarts().add(cart);

        //When
        userDao.save(user);
        productDao.save(product);
        cartDao.save(cart);

        long id = cart.getId();
        Optional<Cart> optionalCart = cartDao.findById(id);
        Cart savedCart = optionalCart.orElseThrow(() -> new RuntimeException("No Cart"));
        savedCart.setName("changedCart");

        cartDao.save(savedCart);
        long changedCartId = savedCart.getId();
        Optional<Cart> optionalChangedCart = cartDao.findById(changedCartId);
        Cart changedCart = optionalChangedCart.orElseThrow(() -> new RuntimeException("No Cart"));

        //Then
        Assert.assertEquals(1, cartDao.findAll().size());
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
        cart.getProductList().add(product);
        product.getCarts().add(cart);

        //When
        userDao.save(user);
        productDao.save(product);
        cartDao.save(cart);

        long id = cart.getId();
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
