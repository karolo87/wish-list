package com.example.wishlist.controllers;

import com.example.wishlist.model.Gift;
import com.example.wishlist.service.GiftService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GiftController {

    private final GiftService giftService;

    @GetMapping("/api/gift")
    public List<Gift> getAllGifts() {
        return giftService.getAllGifts();
    }

    @PostMapping("/api/gift")
    public Gift addNewGift(@RequestBody Gift gift) {
        return giftService.addNewGift(gift);
    }
}
