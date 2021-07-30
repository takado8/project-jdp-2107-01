package com.kodilla.ecommercee.group;

import com.kodilla.ecommercee.group.domain.Group;
import com.kodilla.ecommercee.group.repository.GroupDao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.ArrayList;

import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class GroupTestSuite {

    @Autowired
    private GroupDao groupDao;

    @Test
    public void testGroupSave() {
        //Given
        Group group = new Group(null, "nazwa_grupy", new ArrayList<>());

        //When
        groupDao.save(group);
        long id = group.getId();
        Optional<Group> optionalGroup = groupDao.findById(id);

        //Then
        Assert.assertTrue(optionalGroup.isPresent());

        //CleanUp
        groupDao.deleteById(id);
    }


}
