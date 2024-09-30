package com.kb.dict.service;

import com.kb.dict.dto.Dict;
import com.kb.dict.mapper.DictMapper;
import com.kb.dict.mapper.DictWishMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.sql.*;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DictService {
    private final DictMapper mapper;

    private final DictWishMapper wishMapper;


    public List<Dict> findAll() {
        List<Dict> dict = mapper.selectAll();
        if (dict.isEmpty()) {
            throw new NoSuchElementException();

        }else {
            return dict;
        }

    }
    public Dict findById(Long id) {
        return mapper.selectById(id);
    }

    public List<Dict> search(String word) {
        List<Dict> dict = mapper.Search(word);
        if (dict.isEmpty()) {
            throw new NoSuchElementException();
        } else {
            return dict;
        }
    }


    public Dict updateStatus(Dict dict) {
        System.out.println("Current status: " + dict.getStatus() + ", dino: " + dict.getDino());


        if (dict.getStatus() == 0) {
            dict.setStatus(1);
            wishMapper.insertWish(dict);
        } else if (dict.getStatus() == 1) {
            dict.setStatus(0);
            wishMapper.deleteWish(dict);
        }


        int affectedRows = mapper.updateStatus(dict);
        System.out.println("Affected rows after update: " + affectedRows); // 변경된 행 수 출력

        if (affectedRows == 0) {
            throw new NoSuchElementException("Failed to update status for dict: " + dict.getDino());
        }

        return dict;
    }
}
