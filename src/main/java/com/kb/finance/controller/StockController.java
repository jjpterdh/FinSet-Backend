package com.kb.finance.controller;


import com.kb.finance.dto.Installment;
import com.kb.finance.dto.Stock;
import com.kb.finance.dto.StockChart;
import com.kb.finance.service.InstallmentService;
import com.kb.finance.service.StockService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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

}
