package com.kb.finance.controller;


import com.kb.finance.Scheduler.ForexScheduler;
import com.kb.finance.dto.Forex;
import com.kb.finance.dto.ForexChart;
import com.kb.finance.service.ForexService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/forex")
@Api(value = "ForexController", tags = "외환 정보") // swagger
public class ForexController {
    // 외환
    private final ForexService forexService;
    private final ForexScheduler forexScheduler;

    @GetMapping("")
    public ResponseEntity<List<Forex>> findAll() {
        return ResponseEntity.ok().body(forexService.getAllForex());
    }

    @GetMapping("/{feno}")
    public ResponseEntity<Forex> findByFeno(@PathVariable("feno") long feno) {
        return ResponseEntity.ok(forexService.getForexById(feno));
    }

    @GetMapping("/{feno}/chart")
    public ResponseEntity<List<ForexChart>> findChart(@PathVariable("feno") long feno) {
        return ResponseEntity.ok(forexService.getForexChartById(feno));
    }

    @GetMapping("/runForexBatch")
    @ResponseBody
    public String runForexBatch() {
        try {
            forexScheduler.runForexJob();
            return "Forex batch executed successfully.";
        } catch (Exception e) {
            return "Error executing Forex batch: " + e.getMessage();
        }
    }

}
