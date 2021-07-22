package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.user.UserDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/user")
public class UserController {

    @GetMapping("getAllUsers")
    public void getAllUsers() {
    }

    @GetMapping("getUser")
    public void getUser() {
    }

    @PostMapping("createUser")
    public void createUser(@RequestBody List<UserDto> users) {
    }

    @PutMapping("updateUser")
    public void updateUser(@RequestParam int id) {
    }

    @DeleteMapping("deleteUser")
    public void deleteUser(@RequestParam int id) {
    }
}
