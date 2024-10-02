package com.kb.finance.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Stock {
    private long sno;
    private String stockSymbol;
    private String stockName;
    private int stockPrice;
    private double priceChangeRate;
    private long stockVolume;
    private String imgUrl;

}
