package com.kodilla.ecommercee.product;

import com.kodilla.ecommercee.cart.domain.Cart;
import com.kodilla.ecommercee.cart.repository.CartDao;
import com.kodilla.ecommercee.group.domain.Group;
import com.kodilla.ecommercee.group.repository.GroupDao;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;


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
        List<Product> products = new ArrayList<>();
        Product product1 = new Product("Product1Test",199.99);
        Product product2 = new Product("Product2Test",119.89);
        Product product3 = new Product("Product3Test",62.99);

        Group group = new Group(1L,"jackets",products);

        //When
        products.add(product1);
        products.add(product2);
        products.add(product3);

        Optional<Product> result = productDao.findById(product2.getId());

        //Then
        assertEquals("Product2Test",result.get().getName());

        //Cleanup
        productDao.deleteAll();
    }

    @Test
    public void getAllProducts() {
        //Given
        Product product1 = new Product("TestProduct1Name",199.99);
        Product product2 = new Product("TestProduct2Name",89.99);
        Product product3 = new Product("TestProduct3Name",29.00);
        Product product4 = new Product("TestProduct4Name",9.99);
        List<Product> products = new ArrayList<>();

        //When
        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);

        //Then
        Assert.assertEquals(4,products.size());

        //Cleanup
        productDao.deleteAll();
    }

    @Test
    public void getCreateAndReadProduct() {
        // Given
        Product product = new Product();

        //When
        productDao.save(product);
        Long productId = product.getId();

        //Then
        assertEquals(product, productDao.findById(productId).orElse(null));

        //Cleanup
        productDao.deleteById(productId);
    }

    @Test
    public void testRelationsBetweenProductAndGroup() {
        //Given
        List<Product> productsList = new ArrayList<>();
        Product product1 = new Product();
        Product product2 = new Product();
        Group groupProducts = new Group(5L,"winter jackets",productsList);

        //When
        productDao.save(product1);
        productDao.save(product2);
        groupDao.save(groupProducts);

        //Then
        assertEquals(1, productsList.size());
        assertEquals(product1.getGroupId(),groupProducts.getProducts().get(0));

        //Cleanup
            productDao.deleteAll();
            groupDao.deleteAll();
    }

    @Test
    public void addProductToCart() {
        //Given
        Product product = new Product();
        Cart cart = new Cart();

        //When
        cart.getProducts().add(product);
        product.getCarts().add(cart);
        productDao.save(product);
        long productId = product.getId();

        //Then
        assertEquals(1, cart.getProducts().size());

        //Cleanup
        cartDao.delete(cart);
    }

    @Test
    public void deleteProduct() {
        //Given
        List<Product> productList = new ArrayList<>();
        Product product1 = new Product("winter jacket",699.99);
        Product product2 = new Product("winter boots",499.99);
        Product product3 = new Product("winter pants",299.99);
        Group group = new Group(1L,"winter stuff",productList);

        //When
        groupDao.save(group);
        product1.setId(1L);
        product2.setId(1L);
        product3.setId(1L);
        productList.add(product1);
        productList.add(product2);
        productList.add(product3);

        groupDao.deleteById();

        //Then
        assertEquals(0,group.getProducts().size());
        //Cleanup
        productDao.deleteAll();
        groupDao.deleteAll();
    }

    @Test
    public void testUpdateProduct() {
        //Given

        //When

        //Then

        //Cleanup

    }
}
