package com.kodilla.ecommercee.user.controller;

import com.kodilla.ecommercee.user.UserDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/user")
public class UserController {

    @GetMapping("getAllUsers")
    public void getAllUsers() {
    }

    @PostMapping("createUser")
    public void createUser(@RequestBody UserDto user) {
    }

    @PutMapping("blockUser")
    public void blockUser(@RequestBody UserDto user) {
    }
}