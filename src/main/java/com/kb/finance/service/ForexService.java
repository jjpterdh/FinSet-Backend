package com.kb.finance.service;

import com.kb.finance.dto.Forex;
import com.kb.finance.dto.ForexChart;
import com.kb.finance.mapper.ForexMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ForexService {

    final ForexMapper mapper;
    public List<Forex> getAllForex() {
        List<Forex> list =mapper.selectAll();
        if(list.isEmpty()){
            throw new NoSuchElementException();
        }

        return list;
    }

    public Forex getForexById(Long id) {
        return Optional.ofNullable(mapper.selectById(id))
                .orElseThrow(NoSuchElementException::new);
    }
    public List<ForexChart> getForexChartById(Long id) {
        return Optional.ofNullable(mapper.selectChartById(id))
                .orElseThrow(NoSuchElementException::new);
    }
}
