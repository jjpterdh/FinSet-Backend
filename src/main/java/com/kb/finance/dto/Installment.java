package com.kb.finance.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Installment {
    private long ino; // primary-key
    private String installmentCategory;
    private String installmentName;
    private String installmentBank;
    private String installmentMaxRate;
    private String installmentNormalRate;
    private String installmentJoin;
    private String installmentStream;
    private String installmentAmount;
    private String installmentWay;
    private String installmentTarget;
    private String installmentLink;
    private String imgUrl;
}
