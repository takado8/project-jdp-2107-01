package com.kodilla.ecommercee.user;

import com.kodilla.ecommercee.order.domain.Order;
import com.kodilla.ecommercee.order.service.OrderService;
import com.kodilla.ecommercee.user.domain.User;
import com.kodilla.ecommercee.user.domain.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserMapper {

    private OrderService service;

    @Autowired
    public void setService(OrderService service) {
        this.service = service;
    }

    public User mapToUser(final UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getUsername(),
                userDto.isStatus(),
                userDto.getUser_key(),
                userDto.getEmail(),
                userDto.getPassword(),
                service.getAllOrders().stream()
                        .filter(order -> userDto.getId().equals(order.getUserId().getId()))
                        .collect(Collectors.toList())
        );
    }

    public UserDto mapToUserDao(final User user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.isStatus(),
                user.getUser_key(),
                user.getEmail(),
                user.getPassword(),
                user.getOrders().stream()
                        .map(Order::getId)
                        .collect(Collectors.toList())
        );
    }
}
