package com.kb.dict.controller;

import com.kb.dict.dto.DictWish;
import com.kb.dict.dto.DictWishOrder;
import com.kb.dict.dto.DictWishOrderDTO;
import com.kb.dict.service.DictWishService;
import com.kb.member.dto.Member;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/dictionary/memo")
@RestController
@RequiredArgsConstructor
@Slf4j

@Api(value = "DictwishController", tags = "사전 단어장 즐겨찾기 정보")
public class DictWishController {
    private final  DictWishService dictWishService;
    @GetMapping("")
    public ResponseEntity<List<DictWish>> getList(@AuthenticationPrincipal Member member) {
        return ResponseEntity.ok(dictWishService.getList(member.getUno()));
    }

    @PutMapping("/update/{dwno}")
    public ResponseEntity<?> updateWish(@PathVariable long dwno, @RequestBody DictWish wish) {
        wish.setDwno(dwno);
        int rowsUpdated = dictWishService.updateWish(wish);
        if (rowsUpdated > 0) {
            return ResponseEntity.ok("업데이트 성공");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("업데이트된 행이 없습니다");
        }
    }

    @PatchMapping("/order")
    public ResponseEntity<String> updateOrder(@RequestBody DictWishOrderDTO dictWishOrderDTO) {
        dictWishService.updateWishOrder(dictWishOrderDTO);
        return ResponseEntity.ok("modify order successful");
    }
}
