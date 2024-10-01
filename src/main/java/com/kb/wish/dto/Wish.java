package com.kb.wish.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Wish {
    private int tno; // 상품 타입
    private long uno; // 유저 인덱스
    private int pno; // 상품 인덱스
    private String createdAt;

}
