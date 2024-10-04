package com.kb.search.mapper;

import com.kb.search.dto.Keyword;

import java.util.List;

public interface SearchMapper {
    Keyword selectKeywordByKeno(long keno);
    List<Keyword> selectKeywordList(long keno);
    int insertKeyword(Keyword keyword);
    int deleteKeyword(long keno);
}
