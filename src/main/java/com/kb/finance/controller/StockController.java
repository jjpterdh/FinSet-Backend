package com.kb.finance.controller;


import com.kb.finance.dto.*;
import com.kb.finance.service.StockService;
import com.kb.member.dto.Member;
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
@RequestMapping("/api/stocks")
@Api(value = "StockController", tags = "주식 정보") // swagger
public class StockController {

    // 주식
    private final StockService stockService;

    @GetMapping("")
    public ResponseEntity<List<Stock>> getAllStocks(@RequestParam(value = "sort", defaultValue = "volume") String sort) {

        return ResponseEntity.ok(stockService.getAllStocks(sort));
    }

    @GetMapping("/{sno}")
    public ResponseEntity<Stock> getStock(@PathVariable long sno) {
        return ResponseEntity.ok(stockService.getStockById(sno));
    }

    @GetMapping("/{sno}/chart")
    public ResponseEntity<List<StockChart>> getStockChart(@PathVariable long sno) {
        return ResponseEntity.ok(stockService.getStockChart(sno));
    }

    @GetMapping("/{sno}/symbol")
    public ResponseEntity<StockSymbol> getStockSymbol(@PathVariable long sno) {
        return ResponseEntity.ok(stockService.getStockSymbol(sno));
    }



    @GetMapping("/{sno}/community")
    public ResponseEntity<List<Community>> getCommunities(@PathVariable long sno, @AuthenticationPrincipal Member principal) {
        String email=principal.getUsername();
        System.out.println(email);
        return ResponseEntity.ok(stockService.getCommunities(sno, email));
    }

    @PostMapping("{sno}/community/likes")
    public ResponseEntity<String> addLike(@RequestBody Like like, @AuthenticationPrincipal Member member) {
        like.setUno(member.getUno());
        int likeCount = stockService.findLike(like);  // 기존에 존재하는지 확인
        if (likeCount == 0) {
            stockService.insertLike(like);
            return ResponseEntity.ok("Like added successfully");
        } else {
            return ResponseEntity.ok("Like already exists");
        }
    }

    @DeleteMapping("{sno}/community/likes")
    public ResponseEntity<String> deleteLike(@RequestBody Like like, @AuthenticationPrincipal Member member) {
        like.setUno(member.getUno());
        int wishCount = stockService.findLike(like);
        if (wishCount == 1) {
            stockService.deleteLike(like);
            return ResponseEntity.ok("Like deleted successfully");
        } else {
            return ResponseEntity.ok("Like not found");
        }
    }

}
