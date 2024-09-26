package com.kb.finance.controller;

import com.kb.finance.dto.Installment;
import com.kb.finance.service.InstallmentService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/installments")
@Api(value = "InstallmentController", tags = "적금 정보") // swagger
public class InstallmentController {
    // 적금
    private final InstallmentService installmentService;

    @GetMapping("")
    public ResponseEntity<List<Installment>> getAllInstallments() {
        return ResponseEntity.ok(installmentService.getAllInstallments());
    }

    @GetMapping("/{ino}")
    public ResponseEntity<Installment> getInstallment(@PathVariable long ino) {
        return ResponseEntity.ok(installmentService.getInstallment(ino));
    }
}
