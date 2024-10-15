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

    // 즐겨찾기 추가, 제거
    public int updateWishDict(Dict dict, long uno) {

        List<DictWish> dictWishList = wishMapper.getList(uno);
        long orderSize= dictWishList.size();
        DictWish dictWish = new DictWish();
        dictWish.setDino(dict.getDino());
        dictWish.setUno(uno);
        dictWish.setDictOrder(orderSize+1);

        int result=0;
        int size= wishMapper.getWishByDino(dictWish);
        System.out.println("Dino : "+dict.getDino());
        System.out.println("size: "+size);

        if(size<=0) {
            // 즐겨찾기 추가
            result= wishMapper.insertWish(dictWish);
        }
        else if(size>0) {
            // 즐겨찾기 제거
            result= wishMapper.deleteWish(dictWish);
        }
        // 오류나면 변경
        if(result==0) throw new NoSuchElementException();


        return result;
    }
}
