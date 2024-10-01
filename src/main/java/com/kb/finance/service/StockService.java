package com.kb.finance.service;

import com.kb.finance.dto.*;
import com.kb.finance.mapper.StockMapper;
import com.kb.wish.dto.Wish;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor

public class StockService {
    private final StockMapper mapper;

    public List<Stock> getAllStocks(String sort) {

        Map<String, Object> params = new HashMap<>();
        params.put("sort", sort);
        List<Stock> stocks = mapper.selectAll(params);
        log.info("stocks: {}", stocks);
        if(stocks == null || stocks.isEmpty()) {
            log.info("No stocks found");
            throw new NoSuchElementException();
        }
        return stocks;
    }

    public Stock getStockById(long id) {
        return Optional.ofNullable(mapper.selectById(id))
                .orElseThrow(NoSuchElementException::new);
    }

    public List<StockChart> getStockChart(long id) {
        List<StockChart> stockCharts = mapper.selectChart(id);
        if(stockCharts == null || stockCharts.isEmpty()) {
            log.info("No stocks found");
            throw new NoSuchElementException();
        }
        return stockCharts;
    }

    public StockSymbol getStockSymbol(long id) {
        Stock stock = getStockById(id);
        List<StockChart> stockCharts = getStockChart(id);
        if(stock == null) {
            log.info("No stock found");
            throw new NoSuchElementException();
        }
        List<Integer> minMax = mapper.selectMinMax(id);

        StockSymbol stockSymbol = new StockSymbol();
        stockSymbol.setMinValue(minMax.get(0));
        stockSymbol.setMaxValue(minMax.get(1));
        stockSymbol.setTradingVol(stock.getStockVolume());



        return stockSymbol;
    }

    public List<Community> getCommunities(long sno, long uno) {

        List<Community> communities = mapper.selectCommunity(sno, uno);

        for(Community community : communities) {
            System.out.println(community);
        }

        if(communities == null || communities.isEmpty()) {
            log.info("No communities found");
            throw new NoSuchElementException();
        }

        return communities;
    }

    public int insertCommunityDTO(CommunityDTO communityDTO) {
        int flag=mapper.insertCommunity(communityDTO);
        return flag;
    }

    public int updateCommunityDTO(CommunityDTO communityDTO) {
        int flag=mapper.updateCommunity(communityDTO);
        return flag;
    }

    public int deleteCommunityDTO(CommunityDTO communityDTO) {
        int flag=mapper.deleteCommunity(communityDTO);
        return flag;
    }

    @Transactional(rollbackFor = Exception.class)
    public Like insertLike(Like like) {
        int result = mapper.insertLike(like);
        if (result != 1) {
            throw new NoSuchElementException();
        }
        return like;
    }

    @Transactional(rollbackFor = Exception.class)
    public Like deleteLike(Like like) {
        int result = mapper.deleteLike(like);
        if (result != 1) {
            throw new NoSuchElementException();
        }
        return like;
    }

    public int findLike(Like like) {
        return mapper.findLike(like);
    }


}
