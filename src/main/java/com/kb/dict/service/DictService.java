package com.kb.dict.service;

import com.kb.dict.dto.Dict;
import com.kb.dict.dto.DictWish;
import com.kb.dict.mapper.DictMapper;
import com.kb.dict.mapper.DictWishMapper;
import com.kb.member.dto.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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


    public Dict updateStatus(Dict dict ) {
        System.out.println("Current status: " + dict.getStatus() + ", dino: " + dict.getDino()+ ", uno: " + dict.getUno());

        List<DictWish> dictWishList = wishMapper.getList(dict.getUno());
        long orderSize= dictWishList.size();
        DictWish dictWish = new DictWish();
        dictWish.setDino(dict.getDino());
        dictWish.setUno(dict.getUno());
        dictWish.setDictOrder(orderSize+1);

        if (dict.getStatus() == 0) {
            dict.setStatus(1);  // dict 객체의 속성은 변하지만 db 데이터는 변동 X

            wishMapper.insertWish(dictWish);
            wishMapper.updateStatus(dict);  // updateStatus를 해야 db의 status 변경됨
        } else if (dict.getStatus() == 1) {
            dict.setStatus(0);  // dict 객체의 속성은 변하지만 db 데이터는 변동 X
            wishMapper.deleteWish(dict);
            wishMapper.updateStatus(dict);  // updateStatus를 해야 db의 status 변경됨
        }


        return dict;
    }
}
