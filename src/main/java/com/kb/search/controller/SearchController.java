package com.kb.search.controller;

import com.kb.search.dto.Keyword;
import com.kb.search.service.SearchService;
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
@RequestMapping("/api/search")
@Api(value = "SearchController", tags = "검색 정보")
public class SearchController {
    private final SearchService service;

    @GetMapping("/{uno}") // 최신 검색 키워드 조회
    public ResponseEntity<List<Keyword>> getKeywords(@PathVariable long uno) {
        List<Keyword> keywords = service.getKeywordList(uno);
        return ResponseEntity.ok(keywords);
    }

    @PostMapping("/{uno}") // 검색 키워드 저장
    public ResponseEntity<Keyword> create(@PathVariable long uno, @RequestBody Keyword keyword) {
        keyword.setUno(uno);
        return ResponseEntity.ok(service.createKeyword(keyword));
    }

}