package com.kodilla.ecommercee.product;

import com.kodilla.ecommercee.cart.domain.Cart;
import com.kodilla.ecommercee.cart.repository.CartDao;
import com.kodilla.ecommercee.group.domain.Group;
import com.kodilla.ecommercee.group.repository.GroupDao;
import com.kodilla.ecommercee.order.domain.Order;
import com.kodilla.ecommercee.order.repository.OrderDao;
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
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
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
        List<Product> products = new ArrayList<>();
        Product product1 = new Product("Product1Test",199.99);
        Product product2 = new Product("Product2Test",119.89);
        Product product3 = new Product("Product3Test",62.99);

        //When
        products.add(product1);
        products.add(product2);
        products.add(product3);
        productDao.save(product1);
        productDao.save(product2);
        productDao.save(product3);
        Optional<Product> result = productDao.findById(product2.getId());

        //Then
        assertEquals("Product2Test",result.get().getName());

        //Cleanup
        productDao.deleteAll();
    }

    @Test
    public void getAllProducts() {
        //Given
        List<Product> products = new ArrayList<>();
        Product product1 = new Product("TestProduct1Name",199.99);
        Product product2 = new Product("TestProduct2Name",89.99);
        Product product3 = new Product("TestProduct3Name",29.00);

        //When
        products.add(product1);
        products.add(product2);
        products.add(product3);
        productDao.save(product1);
        productDao.save(product2);
        productDao.save(product3);

        //Then
        Assert.assertEquals(3,products.size());

        //Cleanup
        productDao.deleteAll();
    }

    @Test
    public void createAndReadProduct() {
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
        Product product1 = new Product("product1Name", 299.99);
        Product product2 = new Product("product2Name", 399.99);
        Group groupProducts = new Group(5L,"winter jackets",productsList);

        //When
        productsList.add(product1);
        productsList.add(product2);
        productDao.save(product1);
        productDao.save(product2);
        groupDao.save(groupProducts);

        //Then
        assertEquals(2, productsList.size());
        assertEquals(groupProducts.getProducts().size(),productsList.size());

        //Cleanup
        productDao.deleteAll();
        groupDao.deleteAll();
    }

    @Test
    public void saveProductInDatabase() {
        //Given
        Product product = new Product("testName", 299.99);

        //When
        productDao.save((product));
        Optional<Product> resultProduct = productDao.findById(product.getId());

        //Then
        Assert.assertTrue(resultProduct.isPresent());

        //CleanUp
        productDao.deleteAll();
    }

    @Test
    public void addProductToCart() {
        //Given
        Product product = new Product("product1Name", 299.99);
        Cart cart = new Cart("testName","Test desc", new BigDecimal(299.99), new User());

        //When
        cart.getProducts().add(product);
        product.getCarts().add(cart);
        productDao.save(product);
        cartDao.save(cart);

        //Then
        Long cartId = cart.getId();
        assertNotNull(cartId);
        Cart cartFromDb = cartDao.findById(cartId).orElse(null);
        assertNotNull(cartFromDb);
        List<Product> cartItems = cartFromDb.getProducts();
        assertNotNull(cartItems);
        assertNotEquals(0, cartItems.size());
        assertEquals(1, cartItems.size());
        Product productFromDb = cartItems.get(0);
        Long itemFromDbId = productFromDb.getId();
        Long itemId = product.getId();
        assertNotNull(itemFromDbId);
        assertNotNull(itemId);
        assertEquals(itemFromDbId, itemId);
        //Cleanup
        cartDao.delete(cart);
        productDao.deleteAll();
    }

    @Test
    public void testRelationBetweenProductAndOrder() {
        //Given
        List<Product> productsList = new ArrayList<>();
        Product product = new Product("Testname1",  100.00);
        Product product2 = new Product("Testname2", 200.00);
        Order order = new Order();
        // fix error: order must be saved first, with all @NotNull fields filled up
        order.setDateOfOrder(LocalDate.now());
        order.setPrice(56.66);
        orderDao.save(order);
        //When
        order.setProducts(productsList);
        productDao.save(product);
        productDao.save(product2);
        int productListBeforeDeleting = productsList.size();
        int orderBeforeDeletingProducts = orderDao.findAll().size();

        Long product1Id = product.getId();
        productDao.deleteById(product1Id);

        int productListAfterDeleting = productDao.findAll().size();

        Long orderId = order.getId();
        orderDao.deleteById(orderId);
        int orderAfterDelete = orderDao.findAll().size();

        //Then
        try {
            assertEquals(2, productListBeforeDeleting);
            assertEquals(1, orderBeforeDeletingProducts);
           assertEquals(1, productListAfterDeleting);
            assertEquals(0, orderAfterDelete);

        } catch (AssertionError e) {
            System.out.println("Something went wrong :)");
        }

        //Clean up
        orderDao.deleteAll();
        productDao.deleteAll();
    }

    @Test
    public void deleteProductFromCart() {
        //Given
        Product product = new Product("product1Name", 299.99);
        Cart cart = new Cart("testName","Test desc", new BigDecimal(299.99), new User());

        //When
        cart.getProducts().add(product);
        product.getCarts().add(cart);
        productDao.save(product);
        cartDao.save(cart);
        Long cartId = cart.getId();
        Long productId = product.getId();
        assertNotNull(productId);
        assertNotNull(cartId);

        Cart cartFromDb = cartDao.findById(cartId).orElse(null);
        assertNotNull(cartFromDb);
        List<Product> productsFromDb = cartFromDb.getProducts();
        assertNotNull(productsFromDb);
        assertNotEquals(0, productsFromDb.size());
        assertEquals(1, productsFromDb.size());
        cartFromDb.getProducts().remove(product);
        cartDao.save(cartFromDb);


        Cart cartFromDb2 = cartDao.findById(cartId).orElse(null);
        assertNotNull(cartFromDb2);

        List<Product> fromDb2Products = cartFromDb2.getProducts();
        assertNotNull(fromDb2Products);
        assertEquals(0, fromDb2Products.size());

        // fix error: user must be saved first, with all @NotNull fields filled up
        User user = cart.getUser();
        user.setBlocked(false);
        user.setUserKey("sdfdfsdf");
        user.setEmail("adssdfsdf");
        user.setUsername("asdasdasd");
        user.setStatus(true);
        user.setPassword("sdfsdgsf");
        userDao.save(user);

        // test btw. if removing cart does not remove product from db
        cartDao.deleteById(cartId);
        Product productShouldStillExist = productDao.findById(productId).orElse(null);
        assertNotNull(productShouldStillExist);
        assertEquals(productId, productShouldStillExist.getId());
        //Cleanup
        productDao.deleteById(productId);

        // test btw. if removing cart does not remove user from db
        Long userId = user.getId();
        assertNotNull(userId);
        User userShouldStillExist = userDao.findById(userId).orElse(null);
        assertNotNull(userShouldStillExist);
        assertEquals(userId, userShouldStillExist.getId());
        //Cleanup
        userDao.deleteById(userId);

//        cartDao.deleteById(productId); to usuwa cart, a podane jest id produktu
    }

    @Test
    public void testUpdateProduct() {
        //Given
        List<Product> productList = new ArrayList<>();
        Product product = new Product("winter jacket", 499.99);
        Group group = new Group(1L,"groupName",productList);
        Group updatedGroup = new Group();

        //When
        productList.add(product);
        productDao.save(product);
        product.setName("summer jacket");
        product.setPrice(299.99);
        updatedGroup.setProducts(productList);
        productDao.save(product);
        groupDao.save(group);
        groupDao.save(updatedGroup);
        Product productAfterUpdate = productDao.findById(product.getId()).get();

        //Then
        Assert.assertEquals("summer jacket", product.getName());
        Assert.assertEquals(299.99, productAfterUpdate.getPrice(),0.01);

        //Cleanup
        productDao.delete(product);
        groupDao.delete(group);
        groupDao.delete(updatedGroup);
    }
}
