package com.kb.dict.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DictWish {
    private long dwno;
    private long uno;
    private long dino;
    private String memo;
    private String createdAt;

}
