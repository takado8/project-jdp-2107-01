package com.kodilla.ecommercee.user.service;

import com.kodilla.ecommercee.user.domain.User;
import com.kodilla.ecommercee.user.domain.UserNotFoundException;
import com.kodilla.ecommercee.user.repository.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

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
        if (blockedUser.isBlocked()) {
            userDao.save(blockedUser);
        }
    }

    public User generateUserKey(Long userId) throws UserNotFoundException {
        String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "1234567890";

        String keyGenerateFrom = lowerCase + upperCase + numbers;
        StringBuffer stringKeyGenerator = new StringBuffer();
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            int generator = random.nextInt(keyGenerateFrom.length());
            stringKeyGenerator.append(keyGenerateFrom.charAt(generator));
        }

        String usersKey = stringKeyGenerator.toString();
        Optional<User> user = userDao.findById(userId);
        User foundUser = user.orElseThrow(UserNotFoundException::new);
        if (!foundUser.isBlocked()) {
            foundUser.setUserKey(usersKey);
        }
        userDao.save(foundUser);

        return foundUser;
    }
}
