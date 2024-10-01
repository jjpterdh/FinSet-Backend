package com.kb.finance.service;


import com.kb.finance.dto.Fund;
import com.kb.finance.dto.FundChart;
import com.kb.finance.mapper.FundMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class FundService {
    final FundMapper fundMapper;

    public List<Fund> getProfitFund() {
        List<Fund> list=fundMapper.selectProfit();
        if(list==null||list.size()==0){
            throw new NoSuchElementException();
        }
        return list;
    }

    public List<Fund> getSalesFund() {
        List<Fund> list=fundMapper.selectSales();
        if(list==null||list.size()==0){
            throw new NoSuchElementException();
        }
        return list;
    }

    public List<Fund> getAccFund() {
        List<Fund> list=fundMapper.selectAccumulation();
        if(list==null||list.size()==0){
            throw new NoSuchElementException();
        }
        return list;
    }

    public Fund getFundById(Long id) {
        return Optional.ofNullable(fundMapper.selectFundById(id))
                .orElseThrow(NoSuchElementException::new);
    }

    public List<FundChart> getFundChart(Long id) {
        List<FundChart> list = fundMapper.selectFundChart(id);
        if(list==null||list.size()==0){
            throw new NoSuchElementException();
        }
        return list;
    }
}
