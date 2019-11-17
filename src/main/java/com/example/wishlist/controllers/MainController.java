package com.example.wishlist.controllers;

import com.example.wishlist.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class MainController {

    @GetMapping("/user")
    public List<User> showUsers() {
        User user1 = new User(1L, "Karol", "Ost");
        User user2 = new User(2L, "Mietek", "Lololo");
        User user3 = new User(3L, "Zenek", "Martyniuk");
        return Arrays.asList(user1, user2, user3);
    }
}
