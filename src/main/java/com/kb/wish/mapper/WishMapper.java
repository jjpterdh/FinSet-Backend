package com.kb.wish.mapper;

import com.kb.wish.dto.Wish;

public interface WishMapper {
    int insertWish(Wish wish);
    int deleteWish(Wish wish);
    int findWish(Wish wish);
}
