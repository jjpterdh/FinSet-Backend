package com.kb.finance.controller;


import com.kb.finance.dto.*;
import com.kb.finance.service.InstallmentService;
import com.kb.finance.service.StockService;
import com.kb.member.dto.Member;
import com.kb.security.service.CustomUserDetailsService;
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
        long uno=principal.getUno();
        log.info("uno:"+uno);
        return ResponseEntity.ok(stockService.getCommunities(sno, uno));
    }


}
