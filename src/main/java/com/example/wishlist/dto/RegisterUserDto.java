package com.example.wishlist.dto;

import lombok.Data;

@Data
public class RegisterUserDto {

    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String confirmPassword;
}
