package com.kodilla.ecommercee.product;

import com.kodilla.ecommercee.cart.domain.Cart;
import com.kodilla.ecommercee.cart.repository.CartDao;
import com.kodilla.ecommercee.group.controller.GroupNotFoundException;
import com.kodilla.ecommercee.group.domain.Group;
import com.kodilla.ecommercee.group.repository.GroupDao;
import com.kodilla.ecommercee.order.domain.Order;
import com.kodilla.ecommercee.order.repository.OrderDao;
import com.kodilla.ecommercee.product.domain.Product;
import com.kodilla.ecommercee.product.repository.ProductDao;

import com.kodilla.ecommercee.user.repository.UserDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ProductTestSuite {

    @Autowired
    UserDao userDao;
    @Autowired
    ProductDao productDao;
    @Autowired
    CartDao cartDao;
    @Autowired
    OrderDao orderDao;
    @Autowired
    GroupDao groupDao;

    @Test
    public void getProductById() {
        //Given
        Product product1 = new Product("Product1Test",199.99);
        Product product2 = new Product("Product2Test",119.89);
        Product product3 = new Product("Product3Test",62.99);

        //When
        productDao.save(product1);
        productDao.save(product2);
        productDao.save(product3);
        Optional<Product> result = productDao.findById(product2.getId());

        //Then
        assertEquals("Product2Test",result.get().getName());

        //Cleanup
        productDao.deleteById(product1.getId());
        productDao.deleteById(product2.getId());
        productDao.deleteById(product3.getId());
    }

    @Test
    public void getAllProducts() {
        //Given
        Product product1 = new Product("TestProduct1Name",199.99);
        Product product2 = new Product("TestProduct2Name",89.99);
        Product product3 = new Product("TestProduct3Name",29.00);

        //When
        productDao.save(product1);
        productDao.save(product2);
        productDao.save(product3);

        //Then
        Assert.assertEquals(3,productDao.findAll().size());

        //Cleanup
        productDao.deleteById(product1.getId());
        productDao.deleteById(product2.getId());
        productDao.deleteById(product3.getId());
    }

    @Test
    public void createAndReadProduct() {
        // Given
        Product product = new Product();

        //When
        productDao.save(product);
        Long productId = product.getId();

        //Then
        assertTrue(productDao.findById(productId).isPresent());
        assertEquals(product, productDao.findById(productId).orElse(null));

        //Cleanup
        productDao.deleteById(productId);
    }

    @Test
    public void testUpdateProduct() {
        //Given
        Product product = new Product("winter jacket", 499.99);
        productDao.save(product);

        //When
        product.setName("summer jacket");
        product.setPrice(299.99);
        productDao.save(product);
        Product productAfterUpdate = productDao.findById(product.getId()).get();

        //Then
        Assert.assertEquals(1, productDao.findAll().size());
        Assert.assertNotEquals("winter jacket", productAfterUpdate.getName());
        Assert.assertEquals(299.99, productAfterUpdate.getPrice(),0.01);

        //Cleanup
        productDao.delete(product);
    }

    @Test
    public void testRelationsBetweenProductAndGroup() {
        //Given
        List<Product> productsList = new ArrayList<>();
        Product product1 = new Product("product1Name", 299.99);
        Product product2 = new Product("product2Name", 399.99);
        Group groupProducts = new Group("winter jackets", productsList);

        //When
        productsList.add(product1);
        productsList.add(product2);
        productDao.save(product1);
        productDao.save(product2);
        groupDao.save(groupProducts);
        Group groupFromDb = groupDao.findById(groupProducts.getId()).
                orElseThrow(()-> new GroupNotFoundException("Group with ID :" + groupProducts.getId() +" not found"));

        //Then
        assertEquals(groupFromDb.getProducts().size(), productsList.size());
        productDao.deleteById(product1.getId());
        productDao.deleteById(product2.getId());
        assertTrue(productDao.findAll().isEmpty());
        assertTrue(groupDao.findById(groupProducts.getId()).isPresent());
    }

    @Test
    public void testRelationBetweenProductAndOrder() {
        //Given
        Product product = new Product("Testname1",  100.00);
        Product product2 = new Product("Testname2", 200.00);
        Order order = new Order();

        //When
        order.setDateOfOrder(LocalDate.now());
        order.setPrice(56.66);
        order.getProducts().addAll(Arrays.asList(product, product2));
        orderDao.save(order);
        productDao.save(product);
        productDao.save(product2);



        //Then
        assertEquals(2, orderDao.findById(order.getId()).get().getProducts().size());
        productDao.deleteById(product.getId());
        productDao.deleteById(product2.getId());
        assertTrue(orderDao.findById(order.getId()).isPresent());

        //Clean up
        orderDao.deleteById(order.getId());
    }

    @Test
    public void testRelationBetweenProductAndCart() {
        //Given
        Product product = new Product("product1Name", 299.99);
        Cart cart = new Cart();

        //When
        cart.getProducts().add(product);
        product.getCarts().add(cart);
        productDao.save(product);
        cartDao.save(cart);
        Long cartId = cart.getId();
        Long productId = product.getId();

        //Then
        Assert.assertEquals(1, cartDao.findById(cartId).get().getProducts().size());
        productDao.deleteById(productId);
        Assert.assertTrue(cartDao.findById(cartId).isPresent());

        //CleanUp
        cartDao.deleteById(cartId);
    }
}
