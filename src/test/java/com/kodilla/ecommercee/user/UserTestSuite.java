package com.kodilla.ecommercee.user;

import com.kodilla.ecommercee.order.domain.Order;
import com.kodilla.ecommercee.order.repository.OrderDao;
import com.kodilla.ecommercee.user.domain.User;
import com.kodilla.ecommercee.user.repository.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserTestSuite {

    @Autowired
    private UserDao userDao;

    @Autowired
    private OrderDao orderDao;

    User user = new User(1L, "john", false, "14113", "test@test.co", "test123", null);
    Order order = new Order(525, LocalDate.of(2021, 7, 15), user);
    Order order2 = new Order(122.5, LocalDate.of(2021, 7, 15), user);
    Order order3 = new Order(10524.5, LocalDate.of(2021, 7, 15), user);

    List<Order> orders = new ArrayList<>();

    @Test
    public void testFindUser() {

        //Given
        userDao.save(user);

        //When
        userDao.findById(user.getId());

        //Then
        assertTrue(userDao.findById(user.getId()).isPresent());

        //Cleanup
        userDao.deleteById(user.getId());
    }

    @Test
    public void testSaveUser() {

        //Given
        userDao.save(user);

        //When
        List<User> users = userDao.findAll();

        //Then
        assertEquals(1, users.size());

        //Cleanup
        userDao.deleteById(user.getId());
    }

    @Test
    public void testUpdateUser() {

        //Given
        userDao.save(user);

        //When
        User newUser = userDao.getOne(user.getId());
        newUser.setUsername("Bartłomiej");

        //Then
        assertEquals("Bartłomiej", newUser.getUsername());

        //Cleanup
        userDao.deleteById(user.getId());
    }

    @Test
    public void testDeleteUser() {

        //Given
        userDao.save(user);

        //When
        userDao.deleteById(user.getId());

        //Then
        assertFalse(userDao.existsById(user.getId()));
    }

    @Test
    public void testRelationUserOrder() {

        //Given
        orders.add(order);
        orders.add(order2);
        orders.add(order3);
        user.setOrders(orders);
        userDao.save(user);
        orderDao.save(order);
        orderDao.save(order2);
        orderDao.save(order3);

        //When
        userDao.findById(user.getId());

        //Then
        assertTrue(orderDao.findById(order.getId()).isPresent());
        assertTrue(orderDao.findById(order2.getId()).isPresent());
        assertTrue(orderDao.findById(order3.getId()).isPresent());
        assertTrue(userDao.existsById(user.getId()));

        //Cleanup
        userDao.deleteById(user.getId());
        orderDao.deleteById(order.getId());
        orderDao.deleteById(order2.getId());
        orderDao.deleteById(order3.getId());
    }

    @Test
    public void testDeleteUserDeletesOrder() {

        //Given

        //When

        //Then

        //Cleanup
    }
}
