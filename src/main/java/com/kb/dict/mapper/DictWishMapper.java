package com.kb.dict.mapper;


import com.kb.dict.dto.Dict;
import com.kb.dict.dto.DictWish;
import com.kb.dict.dto.DictWishOrder;
import com.kb.dict.dto.DictWishOrderDTO;

import java.util.List;

public interface DictWishMapper{
    List<DictWish> getList(long uno);
    int deleteWish(Dict dict);
    DictWish get(long id);
    int insertWish(DictWish dictWish);
    int updateWish(DictWish dictWish);
    int updateStatus(Dict dict);
    int updateWishOrder(DictWishOrder dictWishOrder);
}
