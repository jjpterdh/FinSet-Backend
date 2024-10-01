package com.kb.wish.mapper;

import com.kb.wish.dto.Wish;

import java.util.List;

public interface WishMapper {
    List<Wish> getWishesById(long uno);
    int insertWish(Wish wish);
    int deleteWish(Wish wish);
    int findWish(Wish wish);
}
