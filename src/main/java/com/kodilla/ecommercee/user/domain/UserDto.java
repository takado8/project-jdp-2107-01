package com.kodilla.ecommercee.user.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String status;
    private Long userKey;
    private boolean isBlocked;

    public UserDto(Long id, String username, String email, String password, String status, boolean isBlocked) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.status = status;
        this.userKey = generateRandomKey();
        this.isBlocked = isBlocked;
    }

    private Long generateRandomKey() {
        return new Random().nextLong();
    }
}

