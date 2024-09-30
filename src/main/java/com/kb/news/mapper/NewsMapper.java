package com.kb.news.mapper;


import com.kb.news.dto.News;

import java.util.List;

public interface NewsMapper {
    List<News> selectNewsBySno(long sno);
    List<News> selectHomeNews();
}
