package com.kb.kospi.service;

import com.kb.kospi.dto.Kospi;
import com.kb.kospi.mapper.KospiMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class KospiService {
    private final KospiMapper kospiMapper;

    @Transactional
    public List<Kospi> getKospiData() {
        List<Kospi> kospiData = kospiMapper.selectKospiData();
        if (kospiData == null) {
            throw new NoSuchElementException();
        }
        return Optional.of(kospiData).orElseThrow(NoSuchElementException::new);
    }
}
