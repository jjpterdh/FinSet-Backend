package com.kb.kospi.controller;

import com.kb.kospi.service.KospiService;
import com.kb.kospi.dto.Kospi;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Api(value = "KospiController", tags = "코스피 정보")
public class KospiController {
    private final KospiService kospiService;

   @GetMapping("/main/kospi") // 메인 화면 뉴스 조회
   public ResponseEntity<List<Kospi>> getKospi() {
       List<Kospi> kospi = kospiService.getKospiData();
       return ResponseEntity.ok(kospi);
   }

}
