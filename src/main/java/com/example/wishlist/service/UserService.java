package com.example.wishlist.service;

import com.example.wishlist.model.Gift;
import com.example.wishlist.model.User;
import com.example.wishlist.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final GiftService giftService;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User addNewUser(User user) {
        return userRepository.save(user);
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

    public User addNewGiftToUser(Long userId, Gift gift) {
        Optional<User> foundUser = userRepository.findById(userId);
        if (foundUser.isPresent()) {
            User user = foundUser.get();
            giftService.addNewGift(gift);
            user
                    .getGiftList()
                    .add(gift);
            return userRepository.save(user);
        } else {
            return null;
        }
    }
}
