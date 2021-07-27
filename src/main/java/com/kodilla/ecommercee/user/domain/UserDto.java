package com.kodilla.ecommercee.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Random;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long userId;
    private String username;
    private String email;
    private String password;
    private String status;
    private Long userKey;
    private boolean isBlocked;

    public void generateUserKey(Long userKey) {
        Random random = new Random();
        this.userKey = random.nextLong();
    }

    public void blockUser(boolean isBlocked) {
        this.isBlocked = !isBlocked;
    }
}
