package com.kb.finance.controller;


import com.kb.finance.dto.Deposit;
import com.kb.finance.service.DepositService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/deposits")
@Api(value = "DepositController", tags = "예금 정보") // swagger
public class DepositController {
    // 예금
    private final DepositService depositService;

    @GetMapping("")
    public ResponseEntity <List<Deposit>> findAll(@RequestParam(value = "sort", defaultValue = "total") String sort) {

        if(sort.equals("simple")) {
            return ResponseEntity.ok().body(depositService.getSimpleDeposits());
        }

        else if(sort.equals("compound")) {
            return ResponseEntity.ok().body(depositService.getCompoundDeposits());
        }
        else {
            return ResponseEntity.ok().body(depositService.getAllDeposits());
        }

    }



    @GetMapping("/{dno}")
    public ResponseEntity<Deposit> findByDno(@PathVariable("dno") long dno) {
        return ResponseEntity.ok(depositService.getDeposit(dno));
    }
}
