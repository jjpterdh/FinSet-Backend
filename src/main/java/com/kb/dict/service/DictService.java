package com.kb.dict.service;

import com.kb.dict.dto.Dict;
import com.kb.dict.mapper.DictMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DictService {
    private final DictMapper mapper;


    public List<Dict> findAll() {
        List<Dict> dict = mapper.selectAll();
        if (dict.isEmpty()) {
            throw new NoSuchElementException();

        }else {
            return dict;
        }

    }
    public Dict getDict(long id) {
        return Optional.ofNullable(mapper.selectById(id))
                .orElseThrow(NoSuchElementException::new);

    }

    public List<Dict> search(String word) {
        List<Dict> dict=mapper.Search(word);
      if (dict.isEmpty()) {
          throw new NoSuchElementException();
      } else{
          return dict;
      }

    }
}
