package com.kb.dict.mapper;

import com.kb.dict.dto.Dict;

import java.util.List;

public interface DictMapper {
    List<Dict> selectAll();
    Dict selectById(long id);
    List<Dict> Search(String word);

}
