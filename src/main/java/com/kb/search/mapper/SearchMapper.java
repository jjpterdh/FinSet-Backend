package com.kb.search.mapper;

import com.kb.search.dto.Keyword;

public interface SearchMapper {
    Keyword selectKeywordByKeno(long keno);
    int insertKeyword(Keyword keyword);
}
