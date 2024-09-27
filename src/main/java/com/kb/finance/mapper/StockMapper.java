package com.kb.finance.mapper;

import com.kb.finance.dto.Stock;
import com.kb.finance.dto.StockChart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface StockMapper {
    List<Stock> selectAll(Map sort);
    List<StockChart> selectChart(Long id);
    Stock selectById(Long id);
}
