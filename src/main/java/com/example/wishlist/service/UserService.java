package com.example.wishlist.service;

import com.example.wishlist.dto.GiftDto;
import com.example.wishlist.dto.LoginDto;
import com.example.wishlist.dto.RegisterUserDto;
import com.example.wishlist.model.Gift;
import com.example.wishlist.model.User;
import com.example.wishlist.repository.UserRepository;
import com.example.wishlist.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final GiftService giftService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User addNewUser(RegisterUserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(encodePassword(userDto.getPassword()));
        user.setConfirmPassword(encodePassword(userDto.getPassword()));
        user.setGiftList(new ArrayList<>());
        return userRepository.save(user);
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public void deleteUserById(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
        }
    }

    public User getUserById(Long userId) {
        Optional<User> foundUser = userRepository.findById(userId);
        return foundUser.orElse(null);
    }

    public User addNewGiftToUser(Long userId, GiftDto giftDto) {
        Optional<User> foundUser = userRepository.findById(userId);
        if (foundUser.isPresent()) {
            User user = foundUser.get();
            Gift gift = giftService.addNewGift(giftDto);
            user
                    .getGiftList()
                    .add(gift);
            return userRepository.save(user);
        } else {
            return null;
        }
    }

    public AuthenticationResponse login(LoginDto loginDto) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String authenticationToken = jwtProvider.generateToken(authenticate);
        return new AuthenticationResponse(authenticationToken, loginDto.getUsername());
    }

    public Optional<org.springframework.security.core.userdetails.User> getCurrentUser() {
        org.springframework.security.core.userdetails.User principal =
                (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Optional.of(principal);
    }

    public Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getDetails();
        return user.getId();
    }
}
