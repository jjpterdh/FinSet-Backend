package com.kb.finance.mapper;

import com.kb.finance.dto.Stock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface StockMapper {
    List<Stock> selectAll(Map sort);
    Stock selectById(Long id);
}
