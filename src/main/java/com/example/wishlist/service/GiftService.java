package com.example.wishlist.service;

import com.example.wishlist.dto.GiftDto;
import com.example.wishlist.model.Gift;
import com.example.wishlist.repository.GiftRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GiftService {

    private final GiftRepository giftRepository;
//    private final UserService userService;

    public List<Gift> getAllGifts() {
        return giftRepository.findAll();
    }

    public Gift addNewGift(GiftDto giftDto) {
        Gift gift = makeGiftFromDto(giftDto);
//        Optional<User> currentUser = userService.getCurrentUser();
//        if (currentUser.isPresent()) {
//            Long currentUserId = userService.getCurrentUserId();
//            com.example.wishlist.model.User user = userService.getUserById(currentUserId);
//            user
//                    .getGiftList()
//                    .add(gift);
//        }
        return giftRepository.save(gift);
    }

    public void deleteGiftById(Long giftId) {
        if (giftRepository.findById(giftId).isPresent()) {
            giftRepository.deleteById(giftId);
        }
    }

    public Gift makeGiftFromDto(GiftDto giftDto) {
        Gift gift = new Gift();
        gift.setName(giftDto.getName());
        gift.setDescription(giftDto.getDescription());
        gift.setIsReserved(false);
//        User username = userService.getCurrentUser().orElseThrow(()->
//                new IllegalArgumentException("No user logged in!"));
//        gift.setUsername(username.getUsername());
        return gift;
    }

    public GiftDto makeDtoFromGift(Gift gift) {
        GiftDto giftDto = new GiftDto();
        giftDto.setName(gift.getName());
        giftDto.setDescription(gift.getDescription());
        return giftDto;
    }
}
