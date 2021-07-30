package com.kodilla.ecommercee.group.repository;

import com.kodilla.ecommercee.group.domain.Group;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface GroupDao extends CrudRepository<Group, Long> {

    @Override
    List<Group> findAll();

}
