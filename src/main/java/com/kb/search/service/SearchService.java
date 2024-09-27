package com.kb.search.service;

import com.kb.search.dto.Keyword;
import com.kb.search.mapper.SearchMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SearchService {
    private final SearchMapper searchMapper;

    @Transactional
    public Keyword getKeyword(long keno) {
        Keyword keyword = searchMapper.selectKeywordByKeno(keno);
        if (keyword == null) {
            throw new NoSuchElementException();
        }
        return Optional.of(keyword).orElseThrow(NoSuchElementException::new);
    }

    @Transactional(rollbackFor = Exception.class)
    public Keyword createKeyword(Keyword keyword) {
        int result = searchMapper.insertKeyword(keyword);
        if (result != 1) {
            throw  new NoSuchElementException();
        }
        return getKeyword(keyword.getKeno());
    }

}
