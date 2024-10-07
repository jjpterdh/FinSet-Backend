package com.kb.finance.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StockSymbol {
    private String minValue;
    private String maxValue;
    private String openPrice;
    private String closePrice;
    private String tradingVol;
    private String sales;
    private String profit;
    private String income;
}
