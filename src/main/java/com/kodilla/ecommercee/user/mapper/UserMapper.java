package com.kodilla.ecommercee.user.mapper;

import com.kodilla.ecommercee.cart.repository.CartDao;
import com.kodilla.ecommercee.order.domain.Order;
import com.kodilla.ecommercee.order.repository.OrderDao;
import com.kodilla.ecommercee.user.domain.User;
import com.kodilla.ecommercee.user.domain.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserMapper {

    private final OrderDao orderDao;
    private final CartDao cartDao;

    public User mapToUser(final UserDto userDto) {
        User user = new User(
                userDto.getUsername(),
                userDto.isStatus(),
                userDto.getUserKey(),
                userDto.getEmail(),
                userDto.getPassword(),
                userDto.isBlocked());

        return user;
    }

    public UserDto mapToUserDto(final User user) {
        UserDto userDto = new UserDto(
                user.getId(),
                user.getUsername(),
                user.isStatus(),
                user.getUserKey(),
                user.getEmail(),
                user.getPassword(),
                user.isBlocked(),
                user.getOrdersId().stream()
                        .map(Order::getId)
                        .collect(Collectors.toList()));
        return userDto;
    }

    public List<UserDto> mapToUserDtoList(List<User> userList) {
        return userList.stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }
}
