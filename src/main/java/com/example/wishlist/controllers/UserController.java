package com.example.wishlist.controllers;

import com.example.wishlist.dto.LoginDto;
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
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/register")
    public ResponseEntity addNewUser(@RequestBody RegisterUserDto userDto) {
        userService.addNewUser(userDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDto loginDto) {
        return userService.login(loginDto);
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable ("userId") Long userId) {
        return userService.getUserById(userId);
    }

    @DeleteMapping("/{userId}")
    public void deleteUserById(@PathVariable ("userId") Long userId) {
        userService.deleteUserById(userId);
    }

    @PutMapping("/{userId}/add-gift")
    public User addGiftToUser(@PathVariable("userId") Long userId, @RequestBody Gift gift) {
        return userService.addNewGiftToUser(userId, gift);
    }
}
