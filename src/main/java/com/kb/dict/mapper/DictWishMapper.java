package com.kb.dict.mapper;


import com.kb.dict.dto.Dict;
import com.kb.dict.dto.DictWish;
import com.kb.dict.dto.DictWishOrder;

import java.util.List;

public interface DictWishMapper{
    List<DictWish> getList(long uno);
    int deleteWish(Dict dict);
    DictWish get(long id);
    int insertWish(Dict dict);
    int updateWish(DictWish dictWish);
    int updateStatus(Dict dict);
    int updateWishOrder(DictWishOrder dictWishOrder);
    int initWishOrder(DictWishOrder dictWishOrder);
}
