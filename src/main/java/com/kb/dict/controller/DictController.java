package com.kb.dict.controller;

import com.kb.dict.dto.Dict;
import com.kb.dict.mapper.DictMapper;
import com.kb.dict.service.DictService;
import com.kb.finance.dto.Deposit;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dictionary")
@Api(value = "DictController", tags = "사전 정보")
public class DictController {
    final DictService service;

    @GetMapping("")
    public ResponseEntity<List<Dict>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("{dino}")
    public ResponseEntity<Dict> findByDino(@PathVariable("dino") long dino) {
        return ResponseEntity.ok(service.getDict(dino));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Dict>> search(@RequestParam("word") String word) {
        try {
            List<Dict> dict = service.search(word);
            return ResponseEntity.ok(dict);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}