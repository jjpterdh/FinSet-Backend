package com.kb.kospi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Kospi {
    private long kno;
    private String kospiDate;
    private double kospiVal;
}
