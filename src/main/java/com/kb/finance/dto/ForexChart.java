package com.kb.finance.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class ForexChart {
    private long fecno;
    private String forexName;
    private int feno;
    private double forexBasicRate;
    private String forexDatetime;
}
