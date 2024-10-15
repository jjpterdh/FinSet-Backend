package com.kb.dict.service;

import com.kb.dict.dto.Dict;
import com.kb.dict.dto.DictWish;
import com.kb.dict.dto.DictWishOrder;
import com.kb.dict.dto.DictWishOrderDTO;
import com.kb.dict.mapper.DictWishMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DictWishService {
    final DictWishMapper wishMapper;


    public List<DictWish> getList(long uno) {
        List<DictWish> dict = wishMapper.getList(uno);
        if (dict.isEmpty()) {
            throw new NoSuchElementException();
        } else {
            return dict;
        }
    }

    // Dino을 통해서 유저의 즐겨찾기 여부 확인하기
    public int getWishByDino(DictWish dictWish) { //

        return Optional.ofNullable(wishMapper.getWishByDino(dictWish))
                .orElseThrow(NoSuchElementException::new);
    }



    public int updateWish(DictWish wish) {

        return wishMapper.updateWish(wish);
    }

    public int updateWishOrder(DictWishOrderDTO dictWishOrderDTO) {
        List<DictWishOrder> wishOrders = dictWishOrderDTO.getDictWishOrders();
        int result = 1;

        for(DictWishOrder dictWishOrder : wishOrders) {

            if (wishMapper.updateWishOrder(dictWishOrder) != 1) {

                throw new NoSuchElementException();
            }
        }


        return result;
    }


}
