package com.kb.dict.service;

import com.kb.dict.dto.Dict;
import com.kb.dict.dto.DictWish;
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
    public List<DictWish> getList() {
        List<DictWish> dict = wishMapper.getList();
        if (dict.isEmpty()) {
            throw new NoSuchElementException();

        }else {
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


}
