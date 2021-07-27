package com.kodilla.ecommercee.user.controller;

import com.kodilla.ecommercee.user.domain.UserDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("v1/user")
public class UserController {

    @GetMapping("getAllUsers")
    public List<UserDto> getAllUsers() {
        return new ArrayList<>();
    }

    @GetMapping("getUser")
    public UserDto getUser(@RequestParam Long userId) {
        return new UserDto();
    }

    @PostMapping("createUser")
    public void createUser(@RequestBody UserDto user) {
    }

    @PatchMapping("blockUser")
    public void blockUser(@RequestParam Long userId) {
        //...
    }

    @DeleteMapping("deleteUser")
    public void deleteUser(@RequestParam Long userId) {
        //...
    }

    @PutMapping("updateUser")
    public UserDto updateUser(@RequestBody UserDto userDto) {
        return new UserDto();
    }

    @PatchMapping("generateUserKey")
    public UserDto generateUserKey(@RequestParam Long userId) {
        return new UserDto();
    }
}
