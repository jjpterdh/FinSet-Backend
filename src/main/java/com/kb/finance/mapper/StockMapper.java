package com.kb.finance.mapper;

import com.kb.finance.dto.*;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface StockMapper {
    List<Stock> selectAll(Map sort);
    List<StockChart> selectChart(Long id);
    int insertStockChart(StockChart stockChart);
    Stock selectById(Long id);
    List<Community> selectCommunity(@Param("sno")long sno, @Param("uno") long uno, @Param("sort") String sort);
    List<Integer> selectMinMax(Long id);
    int insertCommunity(CommunityDTO communityDTO);
    int updateCommunity(CommunityDTO communityDTO);
    int deleteCommunity(CommunityDTO communityDTO);
    int insertLike(Like like);
    int deleteLike(Like like);
    int findLike(Like like);
}
