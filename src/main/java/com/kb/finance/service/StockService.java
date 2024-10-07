package com.kb.finance.service;

import com.kb.finance.dto.*;
import com.kb.finance.mapper.StockMapper;
import com.kb.testService.dto.StockToken;
import com.kb.testService.service.TokenService;
import com.kb.wish.dto.Wish;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
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

    public StockSymbol getStockSymbol(long id, StockToken stockToken) throws UnsupportedEncodingException, ParseException {
        Stock stock = getStockById(id);
        TokenService tokenService = new TokenService();

        // 헤더 토큰 추가
        HttpHeaders headers = tokenService.getHeaders();
        headers.add("authorization", "Bearer "+stockToken.getAccessToken());

        // 주식 종목 코드 가져오기
        String stockCode=stock.getStockSymbol();

        // 주식현재가_시세 url 불러오기
        String url= tokenService.makeStockUrl(stockCode);
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println(responseEntity.getBody());


        //JSON 파싱
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(responseEntity.getBody());
        JSONObject jsonObject = (JSONObject) obj;
        JSONObject output = (JSONObject) jsonObject.get("output");
        System.out.println("json : "+jsonObject.toJSONString());
        // stock symbol 객체 생성
        StockSymbol stockSymbol = new StockSymbol();
        stockSymbol.setMinValue(output.get("stck_lwpr").toString());
        stockSymbol.setMaxValue(output.get("stck_hgpr").toString());
        stockSymbol.setTradingVol(output.get("acml_tr_pbmn").toString());
        stockSymbol.setOpenPrice(output.get("stck_oprc").toString());
        stockSymbol.setClosePrice(output.get("stck_prpr").toString());


//        stockSymbol.setTradingVol(stock.getStockVolume());



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
