package com.kb.finance.mapper;

import com.kb.finance.dto.Forex;
import com.kb.finance.dto.ForexChart;

import java.util.List;

public interface ForexMapper {
    List<Forex> selectAll();
    Forex selectById(Long id);
    List<ForexChart> selectChartById(Long id);
    Forex selectByName(String name);
    int insertForexData(ForexChart forexChart); // 차트
    int updateForexInfo(Forex forex); // 외환 정보
}
