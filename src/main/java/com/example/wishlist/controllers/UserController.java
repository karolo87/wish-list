package com.example.wishlist.controllers;

import com.example.wishlist.model.User;
import com.example.wishlist.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/api/user")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/api/user")
    public User addNewUser(@RequestBody User user) {
        return userService.addNewUser(user);
    }

    @GetMapping("/api/user/{userId}")
    public User getUserById(@PathVariable ("userId") Long userId) {
        return userService.getUserById(userId);
    }

    @DeleteMapping("/api/user/{userId}")
    public void deleteUserById(@PathVariable ("userId") Long userId) {
        userService.deleteUserById(userId);
    }
}
