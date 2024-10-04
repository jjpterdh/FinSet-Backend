package com.kb.search.service;

import com.kb.search.dto.Keyword;
import com.kb.search.mapper.SearchMapper;
import com.kb.wish.dto.Wish;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SearchService {
    private final SearchMapper searchMapper;

    @Transactional
    public Keyword getKeyword(long keno) {
        Keyword keywords = searchMapper.selectKeywordByKeno(keno);
        if (keywords == null) {
            throw new NoSuchElementException();
        }
        return Optional.of(keywords).orElseThrow(NoSuchElementException::new);
    }

    public List<Keyword> getKeywordList(long keno) {
        List<Keyword> keywordList = searchMapper.selectKeywordList(keno);
        if (keywordList.isEmpty()) {
            throw new NoSuchElementException();
        }
        return keywordList;
    }

    @Transactional(rollbackFor = Exception.class)
    public Keyword createKeyword(Keyword keyword) {
        int result = searchMapper.insertKeyword(keyword);
        if (result != 1) {
            throw  new NoSuchElementException();
        }
        return getKeyword(keyword.getKeno());
    }

    @Transactional(rollbackFor = Exception.class)
    public int deleteKeyword(int keno) {
        int result = searchMapper.deleteKeyword(keno);
        if (result != 1) {
            throw new NoSuchElementException();
        }
        return result;
    }

}
