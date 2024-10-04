package com.kb.search.controller;

import com.kb.member.dto.Member;
import com.kb.search.dto.Keyword;
import com.kb.search.service.SearchService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/search")
@Api(value = "SearchController", tags = "검색 정보")
public class SearchController {
    private final SearchService service;

    @GetMapping("") // 최신 검색 키워드 조회
    public ResponseEntity<List<Keyword>> getKeywords(@AuthenticationPrincipal Member member) {
        List<Keyword> keywords = service.getKeywordList(member.getUno());
        return ResponseEntity.ok(keywords);
    }

    @PostMapping("") // 검색 키워드 저장
    public ResponseEntity<Keyword> createKeyword(@RequestBody Keyword keyword, @AuthenticationPrincipal Member member) {
        keyword.setUno(member.getUno());
        return ResponseEntity.ok(service.createKeyword(keyword));
    }

    @DeleteMapping("/{keno}") // 검색 키워드 삭제
    public ResponseEntity<String> deleteKeyword(@PathVariable("keno") int keno) {
        int result = service.deleteKeyword(keno);
        if (result == 1) {
            return ResponseEntity.ok("Keyword deleted success");
        } else return ResponseEntity.ok("Keyword deleted fail");
    }


}