package com.kodilla.ecommercee.user.service;

import com.kodilla.ecommercee.user.domain.User;
import com.kodilla.ecommercee.user.domain.UserKeyGenerator;
import com.kodilla.ecommercee.user.controller.UserNotFoundException;
import com.kodilla.ecommercee.user.repository.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDbService {

    private final UserDao userDao;
    private final UserKeyGenerator userKeyGenerator;

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
        User blockedUser = userToBlock.orElseThrow(() ->new UserNotFoundException("User with id: " + userId + " not found"));
        if (!blockedUser.isBlocked()) {
            blockedUser.setBlocked(true);
        }
        userDao.save(blockedUser);
    }

    public User generateUserKey(Long userId) throws UserNotFoundException {

        Optional<User> user = userDao.findById(userId);
        User foundUser = user.orElseThrow(() ->new UserNotFoundException("User with id: " + userId + " not found"));
        String generatedKey = userKeyGenerator.keyGenerator();
        if (!foundUser.isBlocked()) {
            foundUser.setUserKey(generatedKey);
        }
        userDao.save(foundUser);

        return foundUser;
    }
}
