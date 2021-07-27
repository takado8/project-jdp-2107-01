package com.kodilla.ecommercee.user.controller;

import com.kodilla.ecommercee.user.domain.UserDto;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("v1/user")
public class UserController {

    @GetMapping("getAllUsers")
    public List<UserDto> getAllUsers() {
        return Arrays.asList(
                new UserDto(1L, "Daniel", "daniel@wp.pl", "123456", "free", false),
                new UserDto(2L, "Jan", "jan@wp.pl", "123456", "free", false),
                new UserDto(3L, "Marek", "marek@wp.pl", "123456", "free", false)
        );
    }

    @PostMapping("createUser")
    public String createUser(@RequestBody UserDto user) {
        return user.getEmail() + " has been created.";
    }

    @PutMapping("blockUser")
    public String blockUser(@RequestBody UserDto user) {
        return user.getEmail() + " has been blocked.";
    }
}