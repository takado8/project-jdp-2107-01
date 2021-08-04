package com.kodilla.ecommercee.order;


import com.kodilla.ecommercee.order.domain.Order;
import com.kodilla.ecommercee.order.repository.OrderDao;
import com.kodilla.ecommercee.product.domain.Product;
import com.kodilla.ecommercee.product.repository.ProductDao;
import com.kodilla.ecommercee.user.domain.User;
import com.kodilla.ecommercee.user.repository.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.StatusResultMatchersExtensionsKt.isEqualTo;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderTestSuite {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private UserDao userDao;


    @Test
    public void testOrderDaoSaveWithProductDaoAndUserDao() {
        //Given
        User user = new User();
        user.setUsername("test_username");
        User user2 = new User();
        user2.setUsername("test_username2");
        Product product = new Product("name product 1", 2469.99);
        Product product2 = new Product("name product 2", 145.71);
        Product product3 = new Product("name product 3", 159.99);
        Product product4 = new Product("name product 4", 15.61);
        Order order = new Order(new BigDecimal("2485.6"), LocalDate.of(2021, 9, 8), user);
        Order order2 = new Order(new BigDecimal("305.7"), LocalDate.of(2021, 9, 8), user2);

        order.getProducts().add(product);
        order.getProducts().add(product4);
        order2.getProducts().add(product2);
        order2.getProducts().add(product3);
        product.getOrders().add(order);
        product4.getOrders().add(order);
        product2.getOrders().add(order2);
        product3.getOrders().add(order2);


        //When
        productDao.save(product);
        productDao.save(product2);
        productDao.save(product3);
        productDao.save(product4);
        orderDao.save(order);
        Long orderId = order.getId();
        orderDao.save(order2);
        Long order2Id = order2.getId();
        userDao.save(user);
        userDao.save(user2);


        //Then
        assertNotEquals(0, orderId, 0.1);
        assertNotEquals(0, order2Id, 0.1);
        assertEquals("test_username", order.getUserId().getUsername());
        assertEquals("test_username2", order2.getUserId().getUsername());


        //Clean up
        productDao.deleteById(product.getId());
        productDao.deleteById(product2.getId());
        productDao.deleteById(product3.getId());
        productDao.deleteById(product4.getId());
        orderDao.deleteById(order.getId());
        orderDao.deleteById(order2.getId());
        userDao.deleteById(user.getId());
        userDao.deleteById(user2.getId());
    }

    @Test
    public void testSaveOrderDao() {
        //Given
        User user = new User();
        User user2 = new User();
        Order order = new Order(new BigDecimal("2485.6"), LocalDate.of(2021, 9, 8), user);
        Order order2 = new Order(new BigDecimal("305.7"), LocalDate.of(2021, 9, 8), user2);

        //When
        userDao.save(user);
        userDao.save(user2);
        orderDao.save(order);
        orderDao.save(order2);

        //Then
        Long orderId = order.getId();
        Long order2Id = order.getId();
        Optional<Order> readOrder = orderDao.findById(orderId);
        Optional<Order> readOrder2 = orderDao.findById(order2Id);
        assertTrue(readOrder.isPresent());
        assertTrue(readOrder2.isPresent());

        //CleanUp
        orderDao.deleteById(order.getId());
        orderDao.deleteById(order2.getId());
        userDao.deleteById(user.getId());
        userDao.deleteById(user2.getId());
    }

    @Test
    public void testDeleteOrderDaoWithoutDeletingUser() {
        //Given
        User user = new User();
        User user2 = new User();
        Order order = new Order(new BigDecimal("2485.6"), LocalDate.of(2021, 9, 8), user);
        Order order2 = new Order(new BigDecimal("305.7"), LocalDate.of(2021, 9, 8), user2);

        //When
        userDao.save(user);
        userDao.save(user2);
        orderDao.save(order);
        orderDao.save(order2);

        //Then
        orderDao.deleteById(order.getId());
        orderDao.deleteById(order2.getId());
        assertFalse(orderDao.findById(order.getId()).isPresent());
        assertFalse(orderDao.findById(order2.getId()).isPresent());
        assertTrue(userDao.findById(user.getId()).isPresent());
        assertTrue(userDao.findById(user2.getId()).isPresent());

        //CleanUp
        try {
            orderDao.deleteById(order.getId());
            orderDao.deleteById(order2.getId());
            userDao.deleteById(user.getId());
            userDao.deleteById(user2.getId());
        } catch (Exception e) {

        }
    }

    @Test
    public void testDeleteOrderDaoWithoutDeletingProduct() {
        //Given
        User user = new User();
        User user2 = new User();
        Order order = new Order(new BigDecimal("2485.6"), LocalDate.of(2021, 9, 8), user);
        Order order2 = new Order(new BigDecimal("305.7"), LocalDate.of(2021, 9, 8), user2);
        Product product = new Product("name product 2", 145.71);
        Product product2 = new Product("name product 3", 159.99);

        order2.getProducts().add(product);
        order2.getProducts().add(product2);

        product.getOrders().add(order2);
        product2.getOrders().add(order2);


        //When
        productDao.save(product);
        productDao.save(product2);
        userDao.save(user);
        userDao.save(user2);
        orderDao.save(order);
        orderDao.save(order2);

        //Then
        orderDao.deleteById(order.getId());
        orderDao.deleteById(order2.getId());
        assertTrue(productDao.findById(product.getId()).isPresent());
        assertTrue(productDao.findById(product2.getId()).isPresent());
        assertFalse(orderDao.findById(order.getId()).isPresent());
        assertFalse(orderDao.findById(order2.getId()).isPresent());
        assertTrue(userDao.findById(user.getId()).isPresent());
        assertTrue(userDao.findById(user2.getId()).isPresent());

        //CleanUp
        try {
            orderDao.deleteById(order.getId());
            orderDao.deleteById(order2.getId());
            userDao.deleteById(user.getId());
            userDao.deleteById(user2.getId());
            productDao.deleteById(product.getId());
            productDao.deleteById(product2.getId());
        } catch (Exception e) {

        }
    }

    @Test
    public void testDeleteProductDaoWithoutDeletingOrder() {
        //Given
        User user = new User();
        User user2 = new User();
        Order order = new Order(new BigDecimal("2485.6"), LocalDate.of(2021, 9, 8), user);
        Order order2 = new Order(new BigDecimal("305.7"), LocalDate.of(2021, 9, 8), user2);
        Product product = new Product("name product 2", 145.71);
        Product product2 = new Product("name product 3", 159.99);

        order2.getProducts().add(product);
        order2.getProducts().add(product2);

        product.getOrders().add(order2);
        product2.getOrders().add(order2);


        //When
        productDao.save(product);
        productDao.save(product2);
        userDao.save(user);
        userDao.save(user2);
        orderDao.save(order);
        orderDao.save(order2);

        //Then
        productDao.deleteById(product.getId());
        productDao.deleteById(product2.getId());
        assertFalse(productDao.findById(product.getId()).isPresent());
        assertFalse(productDao.findById(product2.getId()).isPresent());
        assertTrue(orderDao.findById(order.getId()).isPresent());
        assertTrue(orderDao.findById(order2.getId()).isPresent());
        assertTrue(userDao.findById(user.getId()).isPresent());
        assertTrue(userDao.findById(user2.getId()).isPresent());

        //CleanUp
        try {
            orderDao.deleteById(order.getId());
            orderDao.deleteById(order2.getId());
            userDao.deleteById(user.getId());
            userDao.deleteById(user2.getId());
            productDao.deleteById(product.getId());
            productDao.deleteById(product2.getId());
        } catch (Exception e) {

        }
    }
}
