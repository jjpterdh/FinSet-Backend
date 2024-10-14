package com.kb.finance.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kb.finance.dto.Forex;
import com.kb.finance.dto.ForexChart;
import com.kb.finance.dto.ForexDTO;
import com.kb.finance.mapper.ForexMapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.cert.X509Certificate;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@PropertySource({"classpath:/application.properties"})
public class ForexSchedulerService {
    @Value("${FOREX_API_KEY}") String APIKEY;

    private String authkey = APIKEY;
    private String data = "AP01";
    private final RestTemplate restTemplate;
    private final String searchdate = getSearchdate();
    private final ForexMapper forexMapper;

    @Autowired
    public ForexSchedulerService(ForexMapper forexMapper) {
        this.forexMapper = forexMapper;
        this.restTemplate = createRestTemplateWithSsl();  // SSL 설정이 적용된 RestTemplate 사용
    }

    // SSL 설정이 비활성화된 RestTemplate 생성
    private RestTemplate createRestTemplateWithSsl() {
        try {
            TrustManager[] trustAllCertificates = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[0];
                        }
                    }
            };

            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCertificates, new java.security.SecureRandom());

            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);

            return new RestTemplate();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create SSLContext for RestTemplate", e);
        }
    }


    public JsonNode getExchangeDataSync() {
        String uri = "https://www.koreaexim.go.kr/site/program/financial/exchangeJSON?authkey=" + authkey +
                "&searchdate=" + searchdate + "&data=" + data;
//        System.out.println(uri);
        return parseJson(restTemplate.getForObject(uri, String.class));
    }

    public JsonNode parseJson(String responseBody) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readTree(responseBody);
        } catch (IOException e) {
            // 예외 처리 필요
            e.printStackTrace();
            return null;
        }
    }

    public List<ForexDTO> getForexList() {
        JsonNode jsonNode = getExchangeDataSync();

        if (jsonNode != null && jsonNode.isArray()) {
            List<ForexDTO> forexList = new ArrayList<>();

            for (JsonNode node : jsonNode) {
                ForexDTO forex = convertJsonToForexDto(node);
                forexList.add(forex);
            }

            return forexList;
        }

        return Collections.emptyList();
    }

    public ForexDTO convertJsonToForexDto(JsonNode jsonNode) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.treeToValue(jsonNode, ForexDTO.class);
        } catch (JsonProcessingException e) {
            // 예외 처리 필요
            e.printStackTrace();
            return null;
        }
    }

    public String getSearchdate() {

        LocalDate currentDate = LocalDate.now();
        DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
        // 토요일
        if (dayOfWeek.getValue() == 6)
            return currentDate.minusDays(1).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        // 일요일
        if (dayOfWeek.getValue() == 7)
            return currentDate.minusDays(2).format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        return currentDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

    public void processForexData() {
        List<ForexDTO> forexList = getForexList();
        log.info("Forex data fetched: {}", forexList);

        for (ForexDTO forexDto : forexList) {
            Forex forex = new Forex();
            ForexChart forexChart = new ForexChart();

            // 통화 단위에 따른 feno 설정
            switch (forexDto.getCur_unit()) {
                case "CNH" -> {
                    forex.setFeno(1);
                    forexChart.setFeno(1);
                }
                case "EUR" -> {
                    forex.setFeno(2);
                    forexChart.setFeno(2);
                }
                case "GBP" -> {
                    forex.setFeno(3);
                    forexChart.setFeno(3);
                }
                case "JPY(100)" -> {
                    forex.setFeno(4);
                    forexChart.setFeno(4);
                }
                case "USD" -> {
                    forex.setFeno(5);
                    forexChart.setFeno(5);
                }
                default -> {
                    continue;
                }
            }

            Forex existingForex = forexMapper.selectById(forex.getFeno());

            if (existingForex != null) {
                double basicRate = Double.parseDouble(forexDto.getDeal_bas_r().replace(",",""));
                double buy = Double.parseDouble(forexDto.getTtb().replace(",", ""));
                double sell = Double.parseDouble(forexDto.getTts().replace(",", ""));

                forexChart.setForexName(existingForex.getForexName());
                forexChart.setForexBasicRate(basicRate);

                // ForexChart 데이터 삽입
                int resultInsert = forexMapper.insertForexData(forexChart);
                if (resultInsert != 1) {
                    throw new RuntimeException("Insert Forex Failed for FENO: " + forex.getFeno());
                }

                try {
                    Thread.sleep(1,1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                // 기존 Forex 업데이트
                existingForex.setForexBasicRate(basicRate);
                existingForex.setForexBuy(buy);
                existingForex.setForexSell(sell);
                int resultUpdate = forexMapper.updateForexInfo(existingForex);
                if (resultUpdate != 1) {
                    throw new RuntimeException("Update Forex Failed for FENO: " + forex.getFeno());
                }

                try {
                    Thread.sleep(1,1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                log.info("Processed Forex Data for FENO: {}", forex.getFeno());
            }
        }
    }
}
