package com.kodilla.ecommercee.group;

import com.kodilla.ecommercee.group.domain.Group;
import com.kodilla.ecommercee.group.repository.GroupDao;

import com.kodilla.ecommercee.product.domain.Product;
import com.kodilla.ecommercee.product.repository.ProductDao;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;



@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class GroupTestSuite {

    @Autowired
    private GroupDao groupDao;

    @Autowired
    private ProductDao productDao;

    @Test
    public void testGroupSave() {
        //Given
        Group group = new Group(null,"nazwa_grupy", new ArrayList<>());

        //When
        groupDao.save(group);
        long id = group.getId();
        Optional<Group> optionalGroup = groupDao.findById(id);

        //Then
        assertTrue(optionalGroup.isPresent());

        //CleanUp
        groupDao.deleteById(id);
    }
//
//    @Test
//    public void testAddingProductsToGroup() {
//        //Given
//        Product product1 = new Product("product1", 25.6);
////        Product product2 = new Product("product2", 100.50);
//
//        List<Product> products = Arrays.asList(product1);
//        Group group = new Group("nazwa_grupy", products);
//        product1.setGroupId(group);
////        product2.setGroupId(group);
//        productDao.save(product1);
//
//        //when
//        groupDao.save(group);
//        //then
//        Optional<Group> group1 = groupDao.findById(group.getId());
//        assertTrue(group1.isPresent());
//        Group group2 = group1.get();
//
//        assertEquals(group2.getProducts().get(0).getName(),"product1");
//        List<Product> prods = productDao.findAll();
//        assertEquals(prods.get(0).getName(),"product1");
//    }

    @Test
    public void testShouldNOTProductsBeDeletedWhenDeletingGroup() {
        Group group = new Group(null,"nazwa_grupy", new ArrayList<>());

        //When
        groupDao.save(group);

        Product product1 = new Product(null,"nameeProduct1","desc",
                56.67, group, new ArrayList<>(),new ArrayList<>());

        productDao.save(product1);


        List<Product> products = Arrays.asList(product1);
        group.setProducts(products);

        // print all
        Group groupFromDb = groupDao.findById(group.getId()).orElse(null);
        Product productFromDb = productDao.findById(product1.getId()).orElse(null);

        System.out.println(groupFromDb.getProducts().get(0).getName());
        System.out.println(productFromDb.getName());
        //When
        groupDao.deleteById(group.getId());

        Optional<Product> prods = productDao.findById(product1.getId());
        assertTrue(prods.isPresent());
    }
//
//    @Test
//    public void testWhenDeletingProductGroupShouldntBeDeleted(){
//        //Given
//        Product product1 = new Product("product1", 25.6);
//        Product product2 = new Product("product2", 100.50);
//        List<Product> products = Arrays.asList(product1,product2);
//        Group group = new Group("nazwa_grupy", products);
//        product1.setGroupId(group);
//        product2.setGroupId(group);
//
//        groupDao.save(group);
//
//        //When
//        productDao.deleteById(product2.getId());
//
//        //Then
//        assertTrue(groupDao.existsById(group.getId()));
//    }
//
//    @Test
//    public void testShouldUpdateGroupName(){
//        //Given
//        Group group = new Group("nazwa_grupy", new ArrayList<>());
//        groupDao.save(group);
//
//        //When
//        group.setName("nowa_nazwa");
//        groupDao.save(group);
//        Optional<Group> fetchedGroup = groupDao.findById(group.getId());
//
//        //Then
//        assertTrue(fetchedGroup.isPresent());
//        assertEquals("nowa_nazwa", fetchedGroup.get().getName());
//
//    }
}
