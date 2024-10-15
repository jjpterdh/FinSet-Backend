package com.kb.finance.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class StockChart {
//    private long scno;
    private long sno;
    private String stockDatetime;
    private long stockPrice;
    private double priceChangeRate;
}
