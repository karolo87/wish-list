package com.example.wishlist.controllers;

import com.example.wishlist.dto.RegisterUserDto;
import com.example.wishlist.model.Gift;
import com.example.wishlist.model.User;
import com.example.wishlist.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/api/user/register")
    public ResponseEntity addNewUser(@RequestBody RegisterUserDto userDto) {
        userService.addNewUser(userDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/api/user/{userId}")
    public User getUserById(@PathVariable ("userId") Long userId) {
        return userService.getUserById(userId);
    }

    @DeleteMapping("/api/user/{userId}")
    public void deleteUserById(@PathVariable ("userId") Long userId) {
        userService.deleteUserById(userId);
    }

    @PutMapping("/api/user/{userId}/add-gift")
    public User addGiftToUser(@PathVariable("userId") Long userId, @RequestBody Gift gift) {
        return userService.addNewGiftToUser(userId, gift);
    }
}
