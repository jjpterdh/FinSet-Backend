package com.kb.dict.controller;

import com.kb.dict.dto.Dict;
import com.kb.dict.dto.DictDTO;
import com.kb.dict.mapper.DictMapper;
import com.kb.dict.service.DictService;
import com.kb.dict.service.DictWishService;
import com.kb.finance.dto.Deposit;
import com.kb.member.dto.Member;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dictionary")
@Api(value = "DictController", tags = "사전 정보")
public class DictController {
    final DictService service;
    final DictWishService wishService;

    @GetMapping("")
    public ResponseEntity<List<Dict>> findAll() {

        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{dino}")
    public ResponseEntity<Dict> findByDino(@PathVariable("dino") long dino) {
        return ResponseEntity.ok(service.findById(dino));
    }

    @GetMapping("/search")
    public ResponseEntity<List<DictDTO>> search(@RequestParam("word") String word, @AuthenticationPrincipal Member member) {
        try {
            long uno = member.getUno();

            word=word.replaceAll("\n", "");
            List<DictDTO> dict = service.search(word, uno);
            return ResponseEntity.ok(dict);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    // dino -> dwno 추출하고 -> 즐겨찾기 수정
    @PutMapping("/{dino}")
    public ResponseEntity<Integer> updateWishDict(@PathVariable("dino") long dino, @RequestBody Dict dict, @AuthenticationPrincipal Member principal) {
        dict.setDino(dino);
        long uno=principal.getUno();
        return ResponseEntity.ok(service.updateWishDict(dict, uno));
    }


}