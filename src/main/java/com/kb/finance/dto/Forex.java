package com.kb.finance.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class Forex {
    private long feno; // primary-key
    private String forexName;
    private double forexBasicRate;
    private double forexBuy;
    private double forexSell;
    private String imgUrl;
}
