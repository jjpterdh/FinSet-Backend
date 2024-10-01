package com.kb.finance.mapper;

import com.kb.finance.dto.Fund;
import com.kb.finance.dto.FundChart;

import java.util.List;

public interface FundMapper {
    List<Fund> selectProfit();
    List<Fund> selectSales();
    List<Fund> selectAccumulation();
    Fund selectFundById(Long id);
    List<FundChart> selectFundChart(Long id);
}
