package com.kodilla.ecommercee.user.domain;

import com.kodilla.ecommercee.order.domain.Order;
import com.kodilla.ecommercee.product.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Random;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long userId;
    private String username;
    private boolean status;
    private Long userKey;
    private String email;
    private String password;
    private boolean isBlocked;
    private List<Order> orders;

    public void generateUserKey(Long userKey) {
        Random random = new Random();
        this.userKey = random.nextLong();
    }

    public void blockUser(boolean isBlocked) {
        this.isBlocked = !isBlocked;
    }
}
