package com.example.wishlist.service;

import com.example.wishlist.model.Gift;
import com.example.wishlist.repository.GiftRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GiftService {

    private final GiftRepository giftRepository;

    public List<Gift> getAllGifts() {
        return giftRepository.findAll();
    }

    public Gift addNewGift(Gift gift) {
        return giftRepository.save(gift);
    }

    public void deleteGiftById(Long giftId) {
        if (giftRepository.findById(giftId).isPresent()) {
            giftRepository.deleteById(giftId);
        }
    }
}
