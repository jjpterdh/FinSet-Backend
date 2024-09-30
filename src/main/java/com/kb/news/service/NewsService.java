package com.kb.news.service;

import com.kb.news.dto.News;
import com.kb.news.mapper.NewsMapper;
import com.kb.search.dto.Keyword;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class NewsService {
    private final NewsMapper newsMapper;

    @Transactional
    public List<News> getNews(long sno) {
        List<News> news = newsMapper.selectNewsBySno(sno);
        if (news == null) {
            throw new NoSuchElementException();
        }
        return Optional.of(news).orElseThrow(NoSuchElementException::new);
    }

    @Transactional
    public List<News> getHomeNews() {
        List<News> news = newsMapper.selectHomeNews();
        if (news == null) {
            throw new NoSuchElementException();
        }
        return Optional.of(news).orElseThrow(NoSuchElementException::new);
    }
}
