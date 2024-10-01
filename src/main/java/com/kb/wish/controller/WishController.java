package com.kb.wish.controller;

import com.kb.member.dto.Member;
import com.kb.wish.dto.Wish;
import com.kb.wish.service.WishService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/wishes")
@Api(value = "WishController", tags = "관심목록 정보")
public class WishController {
    private final WishService wishService;

    @GetMapping("")
    public ResponseEntity<List<Wish>> getAllWish(@AuthenticationPrincipal Member member) {
        List<Wish> wishes = wishService.getWishList(member.getUno());
        return ResponseEntity.ok(wishes);
    }


    @PostMapping("")
    public ResponseEntity<String> addWish(@RequestBody Wish wish, @AuthenticationPrincipal Member member) {
        wish.setUno(member.getUno());
        int wishCount = wishService.findWish(wish);  // 기존에 존재하는지 확인
        if (wishCount == 0) {
            wishService.insertWish(wish);
            return ResponseEntity.ok("Wish added successfully");
        } else {
            return ResponseEntity.ok("Wish already exists");
        }
    }

    @DeleteMapping("")
    public ResponseEntity<String> deleteWish(@RequestBody Wish wish, @AuthenticationPrincipal Member member) {
        wish.setUno(member.getUno());
        int wishCount = wishService.findWish(wish);
        if (wishCount == 1) {
            wishService.deleteWish(wish);
            return ResponseEntity.ok("Wish deleted successfully");
        } else {
            return ResponseEntity.ok("Wish not found");
        }
    }
}