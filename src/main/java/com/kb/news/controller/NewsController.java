package com.kb.news.controller;

import com.kb.finance.service.StockService;
import com.kb.news.dto.News;
import com.kb.news.service.NewsService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Api(value = "NewsController", tags = "뉴스 정보")
public class NewsController {
    private final NewsService newsService;

   @GetMapping("/stocks/{sno}/news") // 주식 상세 뉴스 조회
   public ResponseEntity<List<News>> getNews(@PathVariable("sno") long sno) {
       List<News> newsList = newsService.getNews(sno);
       return ResponseEntity.ok(newsList);
   }

   @GetMapping("/main/news") // 메인 화면 뉴스 조회
   public ResponseEntity<List<News>> getHomeNews() {
       List<News> newsList = newsService.getHomeNews();
       return ResponseEntity.ok(newsList);
   }

}
