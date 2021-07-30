package com.kodilla.ecommercee.group;

import com.kodilla.ecommercee.group.domain.Group;
import com.kodilla.ecommercee.group.repository.GroupDao;

import com.kodilla.ecommercee.product.domain.Product;
import com.kodilla.ecommercee.product.repository.ProductDao;
import org.junit.Assert;
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
        Group group = new Group("nazwa_grupy", new ArrayList<>());

        //When
        groupDao.save(group);
        long id = group.getId();
        Optional<Group> optionalGroup = groupDao.findById(id);

        //Then
        Assert.assertTrue(optionalGroup.isPresent());

        //CleanUp
        groupDao.deleteById(id);
    }

    @Test
    public void testShouldProductsBeDeletedWhenDeletingGroup(){
        //Given
        Product product1 = new Product("product1", 25.6);
        Product product2 = new Product("product2", 100.50);
        List<Product> products = Arrays.asList(product1,product2);
        Group group = new Group("nazwa_grupy", products);
        product1.setGroupId(group);
        product2.setGroupId(group);

        groupDao.save(group);

        //When
        groupDao.deleteById(group.getId());

        //Then
        Assert.assertFalse(groupDao.existsById(group.getId()));
        Assert.assertFalse(productDao.existsById(product1.getId()));
        Assert.assertFalse(productDao.existsById(product2.getId()));
    }

    @Test
    public void testWhenDeletingProductGroupShouldntBeDeleted(){
        //Given
        Product product1 = new Product("product1", 25.6);
        Product product2 = new Product("product2", 100.50);
        List<Product> products = Arrays.asList(product1,product2);
        Group group = new Group("nazwa_grupy", products);
        product1.setGroupId(group);
        product2.setGroupId(group);

        groupDao.save(group);

        //When
        productDao.deleteById(product2.getId());

        //Then
        Assert.assertTrue(groupDao.existsById(group.getId()));
    }

    @Test
    public void testShouldUpdateGroupName(){
        //Given
        Group group = new Group("nazwa_grupy", new ArrayList<>());
        groupDao.save(group);

        //When
        group.setName("nowa_nazwa");
        groupDao.save(group);
        Optional<Group> fetchedGroup = groupDao.findById(group.getId());

        //Then
        Assert.assertTrue(fetchedGroup.isPresent());
        Assert.assertEquals("nowa_nazwa", fetchedGroup.get().getName());

    }
}
