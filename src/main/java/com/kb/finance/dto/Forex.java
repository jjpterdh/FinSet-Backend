package com.kb.finance.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class Forex {
    private long feno; // primary-key
    private String forexName; // cur_unit
    private double forexBasicRate; // deal_bas_r
    private double forexBuy; // ttb
    private double forexSell; // tts
    private String imgUrl;
}
