package com.kodilla.ecommercee.user.mapper;

import com.kodilla.ecommercee.user.domain.User;
import com.kodilla.ecommercee.user.domain.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserMapper {

    public User mapToUser(final UserDto userDto) {
        return new User(
                userDto.getUserId(),
                userDto.getUsername(),
                userDto.isStatus(),
                userDto.getUserKey(),
                userDto.getEmail(),
                userDto.getPassword(),
                userDto.isBlocked(),
                userDto.getOrders());
    }

    public UserDto mapToUserDto(final User user) {
        return new UserDto(
                user.getUserId(),
                user.getUsername(),
                user.isStatus(),
                user.getUserKey(),
                user.getEmail(),
                user.getPassword(),
                user.isBlocked(),
                user.getOrders());
    }

    public List<UserDto> mapToUserDtoList(List<User> userList) {
        return userList.stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }
}
