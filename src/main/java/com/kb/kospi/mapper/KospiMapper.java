package com.kb.kospi.mapper;


import com.kb.kospi.dto.Kospi;

import java.util.List;

public interface KospiMapper {
    List<Kospi> selectKospiData();
}
