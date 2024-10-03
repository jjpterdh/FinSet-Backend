package com.kb.dict.controller;

import com.kb.dict.dto.Dict;
import com.kb.dict.dto.DictWish;
import com.kb.dict.service.DictWishService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/dictionary/memo")
@RestController
@RequiredArgsConstructor
@Slf4j
@Api(value = "DictwishController", tags = "사전 단어장 정보")
public class DictWishController {
    private final  DictWishService dictWishService;
    @GetMapping("")
    public ResponseEntity<List<DictWish>> getList(@RequestParam long uno) {
        return ResponseEntity.ok(dictWishService.getList(uno));
    }

    @GetMapping("/{dwno}")
    public ResponseEntity<DictWish> get(@PathVariable("dwno") long dwno) {
        return ResponseEntity.ok(dictWishService.get(dwno));

    }

    @PutMapping("/update/{dwno}")
    public ResponseEntity<?> updateWish(@PathVariable long dwno,@RequestBody DictWish wish) {
        wish.setDwno(dwno);
        int rowsUpdated = dictWishService.updateWish(wish);
        if (rowsUpdated > 0) {
            return ResponseEntity.ok("업데이트 성공");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("업데이트된 행이 없습니다");
        }
    }
}
