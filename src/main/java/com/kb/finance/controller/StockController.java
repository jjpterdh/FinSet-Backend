package com.kb.finance.controller;


import com.kb.finance.dto.*;
import com.kb.finance.service.StockService;
import com.kb.member.dto.Member;
import com.kb.testService.dto.StockToken;
import com.kb.testService.service.TokenService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stocks")
@Api(value = "StockController", tags = "주식 정보") // swagger
public class StockController {

    // 주식
    private final StockService stockService;

    // 토큰발급
    private final TokenService tokenService;
    private StockToken stockToken;
    @GetMapping("")
    public ResponseEntity<List<Stock>> getAllStocks(@RequestParam(value = "sort", defaultValue = "volume") String sort) {

        return ResponseEntity.ok(stockService.getAllStocks(sort));
    }

    @GetMapping("/{sno}")
    public ResponseEntity<Stock> getStock(@PathVariable long sno) {
        return ResponseEntity.ok(stockService.getStockById(sno));
    }

    @GetMapping("/{sno}/chart")
    public ResponseEntity<List<StockChart>> getStockChart(@PathVariable long sno) throws ParseException, UnsupportedEncodingException {
        stockToken=tokenService.getDefaultStockToken();
        return ResponseEntity.ok(stockService.getStockChart(sno, stockToken));
    }

    @GetMapping("/{sno}/symbol")
    public ResponseEntity<StockSymbol> getStockSymbol(@PathVariable long sno) throws ParseException, UnsupportedEncodingException {
        stockToken=tokenService.getDefaultStockToken();

//        if(stockToken == null) {
//            stockToken=tokenService.fetch();
//        }
        // 문자열을 LocalDateTime으로 변환
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime givenDateTime = LocalDateTime.parse(stockToken.getAccessTokenTokenExpired(), formatter);
        // 현재 시간 가져오기
        LocalDateTime currentDateTime = LocalDateTime.now();

        // 토큰 만료 시 재발급
//        if(currentDateTime.isAfter(givenDateTime)) {
//            stockToken=tokenService.fetch();
//        }

        return ResponseEntity.ok(stockService.getStockSymbol(sno, stockToken));

    }


    @GetMapping("/{sno}/community")
    public ResponseEntity<List<Community>> getCommunities(@RequestParam(required = false, value = "sort", defaultValue = "latest") String sort, @PathVariable long sno, @AuthenticationPrincipal Member principal) {
        long uno=principal.getUno();
        log.info("uno:"+uno);
        return ResponseEntity.ok(stockService.getCommunities(sno, uno, sort));
    }

    @PostMapping("{sno}/community")
    public ResponseEntity<String> createCommunity(@PathVariable long sno, @RequestBody CommunityDTO communityDTO, @AuthenticationPrincipal Member principal) {
        long uno=principal.getUno();
        communityDTO.setUno(uno);
        communityDTO.setSno(sno);

        int flag=stockService.insertCommunityDTO(communityDTO);
        if(flag==0)
            return ResponseEntity.ok("post failed");
        else
            return ResponseEntity.ok("post success");
    }

    @PatchMapping("{sno}/community/{bno}")
    public ResponseEntity<String> updateCommunity(@PathVariable long sno, @PathVariable long bno, @RequestBody CommunityDTO communityDTO, @AuthenticationPrincipal Member principal) {
        long uno=principal.getUno();
        communityDTO.setUno(uno);
        communityDTO.setSno(sno);
        communityDTO.setBno(bno);
        int flag=stockService.updateCommunityDTO(communityDTO);
        if(flag==0)
            return ResponseEntity.ok("update failed");
        else
            return ResponseEntity.ok("update success");

    }

    @DeleteMapping("{sno}/community/{bno}")
    public ResponseEntity<String> deleteCommunity(@PathVariable long sno, @PathVariable long bno, @AuthenticationPrincipal Member principal) {
        CommunityDTO communityDTO = new CommunityDTO();
        communityDTO.setSno(sno);
        communityDTO.setBno(bno);
        communityDTO.setUno(principal.getUno());
        int flag=stockService.deleteCommunityDTO(communityDTO);
        if(flag==0)
            return ResponseEntity.ok("delete failed");
        else
            return ResponseEntity.ok("delete success");
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
