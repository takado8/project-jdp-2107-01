package com.kodilla.ecommercee.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private boolean status;
    private String user_key;
    private String email;
    private String password;
    private List<Long> ordersId;
}
