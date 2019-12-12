package com.example.wishlist.controllers;

import com.example.wishlist.dto.GiftDto;
import com.example.wishlist.model.Gift;
import com.example.wishlist.service.GiftService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity addNewGift(@RequestBody GiftDto giftDto) {
        giftService.addNewGift(giftDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/api/gift/{giftId}")
    public Gift getGiftById(@PathVariable("giftId") Long giftId) {
        return giftService.getGiftById(giftId);
    }
}
