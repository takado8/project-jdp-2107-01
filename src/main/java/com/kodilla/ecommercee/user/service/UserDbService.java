package com.kodilla.ecommercee.user.service;

import com.kodilla.ecommercee.user.domain.User;
import com.kodilla.ecommercee.user.domain.UserNotFoundException;
import com.kodilla.ecommercee.user.repository.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDbService {

    private UserDao userDao;

    public User save(User user) {
        return userDao.save(user);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    public Optional<User> findById(Long userId) {
        return userDao.findById(userId);
    }

    public void deleteById(Long userId) {
        userDao.deleteById(userId);
    }

    public void blockUser(Long userId) throws UserNotFoundException {
        Optional<User> userToBlock = userDao.findById(userId);
        User blockedUser = userToBlock.orElseThrow(UserNotFoundException::new);
        if (blockedUser.isBlocked() == true) {
            userDao.save(blockedUser);
        }
    }
}
