package com.kb.dict.mapper;


import com.kb.dict.dto.Dict;
import com.kb.dict.dto.DictWish;

import java.util.List;

public interface DictWishMapper{
    List<DictWish> getList();
    int deleteWish(Dict dict);
    DictWish get(long id);
    int insertWish(Dict dict);
    int updateWish(DictWish dictWish);
}
