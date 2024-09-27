package com.kb.finance.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class ForexChart {
    private long fecno;
    private double forexBasicRate;
    private String forexDatetime;
    private String forexName;
}
