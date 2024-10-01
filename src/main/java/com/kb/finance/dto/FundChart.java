package com.kb.finance.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class FundChart {
    private long fcno;
    private String fundName;
    private String fundDatetime;
    private double fundVal;
    private double benVal;
    private double typeVal;
}
