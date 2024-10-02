package com.kb.finance.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Deposit {
    private long dno; // primary-key
    private String depositCategory;
    private String depositName;
    private String depositBank;
    private String depositMaxRate;
    private String depositNormalRate;
    private String depositJoin;
    private String depositStream;
    private String depositAmount;
    private String depositWay;
    private String depositTarget;
    private String depositLink;
    private String imgUrl;




}
