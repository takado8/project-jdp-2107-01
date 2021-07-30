package com.kodilla.ecommercee.user.repository;

import com.kodilla.ecommercee.user.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface UserDao extends CrudRepository<User, Long> {

    @Override
    User save(User user);

    @Override
    List<User> findAll();

    @Override
    Optional<User> findById(Long userId);

    void deleteById(Long userId);
}
