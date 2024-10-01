package com.kb.wish.service;

import com.kb.wish.dto.Wish;
import com.kb.wish.mapper.WishMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class WishService {
    private final WishMapper wishMapper;

    public List<Wish> getWishList(long uno) {
        List<Wish> wishes = wishMapper.getWishesById(uno);
        if (wishes.isEmpty()) {
            throw new NoSuchElementException();
        }
        return wishes;
    }


    @Transactional(rollbackFor = Exception.class)
    public Wish insertWish(Wish wish) {
        int result = wishMapper.insertWish(wish);
        if (result != 1) {
            throw new NoSuchElementException();
        }
        return wish;
    }

    @Transactional(rollbackFor = Exception.class)
    public Wish deleteWish(Wish wish) {
        int result = wishMapper.deleteWish(wish);
        if (result != 1) {
            throw new NoSuchElementException();
        }
        return wish;
    }

    public int findWish(Wish wish) {
        return wishMapper.findWish(wish);
    }

}
