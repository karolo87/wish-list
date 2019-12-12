package com.example.wishlist.service;

import com.example.wishlist.dto.GiftDto;
import com.example.wishlist.exception.WishListException;
import com.example.wishlist.model.Gift;
import com.example.wishlist.repository.GiftRepository;
import com.example.wishlist.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GiftService {

    private final GiftRepository giftRepository;
    private final JwtProvider jwtProvider;
    private final UserService userService;

    public List<Gift> getAllGifts() {
        return giftRepository.findAll();
    }

    public Gift addNewGift(GiftDto giftDto) {
        Gift gift = makeGiftFromDto(giftDto);
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
        User user = userService.getCurrentUser().orElseThrow(() -> new IllegalArgumentException("No user logged in."));
        gift.setUsername(user.getUsername());

        userService.findUserByUsername(user.getUsername())
                .getGiftList().add(gift);

        return gift;
    }

    public GiftDto makeDtoFromGift(Gift gift) {
        GiftDto giftDto = new GiftDto();
        giftDto.setName(gift.getName());
        giftDto.setDescription(gift.getDescription());
        return giftDto;
    }

    public Gift getGiftById(Long giftId) {
        Optional<Gift> gift = giftRepository.findById(giftId);
        return gift.orElseThrow(()-> new WishListException("Gift not found"));
    }
}
