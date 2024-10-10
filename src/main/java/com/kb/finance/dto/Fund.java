package com.kb.finance.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class Fund {
    private long fno;
    private String fundCategory;
    private String fundName;
    private double fundEarningRatio;
    private double fundStandard;
    private double fundChangeRate;
    private double fundScale;
    private String fundCompany;
    private String fundLisk;
    private String fundType;
    private String fundCharge;
    private String fundRegdate;
    private String imgUrl;
    private String fundLink;

}
