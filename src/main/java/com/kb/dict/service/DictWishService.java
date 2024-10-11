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


    public DictWish get(long id) {
        return Optional.ofNullable(wishMapper.get(id))
                .orElseThrow(NoSuchElementException::new);

    }
    public int updateWish(DictWish wish) {
       return wishMapper.updateWish(wish);
    }

    public int updateWishOrder(DictWishOrderDTO dictWishOrderDTO) {
        List<DictWishOrder> wishOrders = dictWishOrderDTO.getDictWishOrders();
        int result = 0;

        DictWishOrder dictWishOrder1 = wishOrders.get(0);
        DictWishOrder dictWishOrder2 = wishOrders.get(1);

        result = wishMapper.initWishOrder(dictWishOrder2);
        if (result != 1) {
            throw new NoSuchElementException("Init failed for order: " + dictWishOrder2.getDwno());
        }
        result = wishMapper.updateWishOrder(dictWishOrder1);
        if (result != 1) {
            throw new NoSuchElementException("Update failed for order: " + dictWishOrder1.getDwno());
        }

        dictWishOrder2.setDwno(-1);
        System.out.println(dictWishOrder2.getDwno());
        result = wishMapper.updateWishOrder(dictWishOrder2);
        if (result != 1) {
            throw new NoSuchElementException("Update failed for order: " + dictWishOrder2.getDwno());
        }

        return result;
    }


}
