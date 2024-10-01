package com.kb.finance.controller;


import com.kb.finance.dto.Fund;
import com.kb.finance.dto.FundChart;
import com.kb.finance.service.FundService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/funds")
@Api(value = "FundController", tags = "펀드 정보") // swagger
public class FundController {
    // 펀드
    final FundService fundService;
    @GetMapping("")
    public ResponseEntity<List<Fund>> getAllFunds(@RequestParam(value="sort", defaultValue = "profit") String sort) {
        if(sort.equals("profit")) {
            return ResponseEntity.ok(fundService.getProfitFund());
        }

        else if(sort.equals("sales")) {
            return ResponseEntity.ok(fundService.getSalesFund());
        }
        else {
            return ResponseEntity.ok(fundService.getAccFund());
        }
    }

    @GetMapping("/{fno}")
    public ResponseEntity<Fund> getFund(@PathVariable("fno") long fno) {
        return ResponseEntity.ok(fundService.getFundById(fno));
    }


    @GetMapping("/{fno}/chart")
    public ResponseEntity<List<FundChart>> getFundChart(@PathVariable("fno") long fno) {
        return ResponseEntity.ok(fundService.getFundChart(fno));
    }
}
