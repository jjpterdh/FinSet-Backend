package com.kb.finance.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StockSymbol {
    private int minValue;
    private int maxValue;
    private int openPrice;
    private int endPrice;
    private int closePrice;
    private long tradingVol;
    private String sales;
    private String profit;
    private String income;
}
