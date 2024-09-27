package com.kb.finance.mapper;

import com.kb.finance.dto.Forex;

import java.util.List;

public interface ForexMapper {
    List<Forex> selectAll();
    Forex selectById(Long id);
}
