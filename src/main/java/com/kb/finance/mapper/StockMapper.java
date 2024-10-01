package com.kb.finance.mapper;

import com.kb.finance.dto.Community;
import com.kb.finance.dto.Like;
import com.kb.finance.dto.Stock;
import com.kb.finance.dto.StockChart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface StockMapper {
    List<Stock> selectAll(Map sort);
    List<StockChart> selectChart(Long id);
    Stock selectById(Long id);
    List<Community> selectCommunity(HashMap<String, Object> param);
    List<Integer> selectMinMax(Long id);

    int insertLike(Like like);
    int deleteLike(Like like);
    int findLike(Like like);
}
