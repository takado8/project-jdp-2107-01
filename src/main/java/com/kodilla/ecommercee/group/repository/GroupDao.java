package com.kodilla.ecommercee.group.repository;

import com.kodilla.ecommercee.group.domain.Group;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface GroupDao extends CrudRepository<Group, Long> {
    List<Group> findAll();

    @Override
    default void deleteById(Long aLong){
        throw new UnsupportedOperationException();
    }

    @Override
    default void delete(Group entity){
        throw new UnsupportedOperationException();
    }

    @Override
    default void deleteAll(Iterable<? extends Group> entities){
        throw new UnsupportedOperationException();
    }

    @Override
    default void deleteAll(){
        throw new UnsupportedOperationException();
    }
}
