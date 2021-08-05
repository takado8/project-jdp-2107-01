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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

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
        Product product = new Product("Nike Runner", 299);
        Group group = new Group("Buty", new ArrayList<>());
        group.getProducts().add(product);
        productDao.save(product);
        groupDao.save(group);

        //When
        Long groupId = group.getId();
        Group savedGroup = groupDao.findById(groupId).orElseThrow(RuntimeException::new);

        //Then
        Assert.assertNotNull(savedGroup);

        //CleanUp
        groupDao.deleteById(groupId);
    }

    @Test
    public void testGroupUpdate() {
        //Given
        Product product = new Product("Nike Runner", 299);
        Product anotherProduct = new Product( "Bluza", 169);
        Group group = new Group("Buty", new ArrayList<>());
        group.getProducts().add(product);
        productDao.save(product);
        productDao.save(anotherProduct);
        groupDao.save(group);

        //When
        Long groupId = group.getId();
        Group savedGroup = groupDao.findById(groupId).orElseThrow(RuntimeException::new);
        savedGroup.setName("Odzież");
        savedGroup.getProducts().add(anotherProduct);
        groupDao.save(savedGroup);
        Group updatedGroup = groupDao.findById(groupId).orElseThrow(RuntimeException::new);

        //Then
        Assert.assertEquals("Odzież", updatedGroup.getName());
        Assert.assertEquals(2, updatedGroup.getProducts().size());
        Assert.assertEquals("Bluza" , updatedGroup.getProducts().get(1).getName());

        //CleanUp
        groupDao.deleteById(groupId);
    }

    @Test
    public void testProductsShouldStayWhenGroupDeleted() {
        //Given
        Product product = new Product("Nike Runner", 299);
        Product anotherProduct = new Product( "Bluza", 169);
        Group group = new Group("Buty", new ArrayList<>());
        group.getProducts().add(product);
        productDao.save(product);
        productDao.save(anotherProduct);
        groupDao.save(group);

        //When
        Long groupId = group.getId();
        Long productId = product.getId();
        Long anotherProductId = anotherProduct.getId();
        groupDao.deleteById(groupId);

        //Then
        Assert.assertFalse(groupDao.findById(groupId).isPresent());
        Assert.assertTrue(productDao.findById(productId).isPresent());
        Assert.assertTrue(productDao.findById(anotherProductId).isPresent());

        //CleanUp
        productDao.deleteById(productId);
        productDao.deleteById(anotherProductId);
    }
}
