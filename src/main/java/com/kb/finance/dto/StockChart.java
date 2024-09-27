package com.kb.finance.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class StockChart {
    private long scno;
    private String stockSymbol;
    private String stockDatetime;
    private long stockPrice;
}
