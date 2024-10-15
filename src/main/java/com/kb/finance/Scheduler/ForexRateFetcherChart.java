package com.kb.finance.Scheduler;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class ForexRateFetcherChart {

    private static final String BASE_API_URL = "https://www.koreaexim.go.kr/site/program/financial/exchangeJSON?authkey=HRCTvW7p8Q2qmyIGylo6R458wFDVAOta&searchdate=%s&data=AP01";
    private static final String API_DATE_FORMAT = "yyyyMMdd";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/finset";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "20020607";

    // 필터링할 통화 목록
    private static final String[] TARGET_CURRENCIES = {"USD", "JPY(100)", "EUR", "GBP", "CNH"};

    public static void main(String[] args) {
        try {
            // 오늘 날짜 가져오기
            LocalDate today = LocalDate.now();
            // 한 달 전 날짜 계산
            LocalDate oneMonthAgo = today.minus(1, ChronoUnit.MONTHS);

            // 한 달 전부터 오늘까지 반복하면서 API 호출
            for (LocalDate date = oneMonthAgo; !date.isAfter(today); date = date.plusDays(1)) {
                // 날짜를 "yyyyMMdd" 형식으로 포맷팅
                String formattedDate = date.format(DateTimeFormatter.ofPattern(API_DATE_FORMAT));
                String apiUrl = BASE_API_URL.replace("%s", formattedDate);

                // API 호출 및 JSON 처리
                String response = sendGetRequest(apiUrl);
                processApiResponse(response, date);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String sendGetRequest(String apiUrl) throws Exception {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // 응답 내용 출력
        System.out.println("API Response: " + response.toString());

        return response.toString();
    }

    private static void processApiResponse(String response, LocalDate date) {
        // JSON 배열 처리
        JSONArray jsonArray;

        try {
            // JSONParser를 사용하여 JSON 배열로 변환
            JSONParser parser = new JSONParser();
            jsonArray = (JSONArray) parser.parse(response);
        } catch (Exception e) {
            e.printStackTrace();
            return; // JSON 파싱 오류 시 메서드 종료
        }

        // 데이터베이스 저장 준비
        String sql = "INSERT INTO tbl_forex_chart (forex_basic_rate, forex_datetime, feno) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // 날짜를 "yyyy-MM-dd" 형식으로 변환
            String formattedDate = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            System.out.println("Formatted Date: " + formattedDate); // 날짜 확인

            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                // 필드 값 확인 및 문자열로 변환
                String curUnit = (String) jsonObject.get("cur_unit");
                String basicRate = (String) jsonObject.get("deal_bas_r");

                // 필터링: 대상 통화 목록에 포함된 경우만 처리
                if (isTargetCurrency(curUnit)) {
                    // feno 값을 가져오기
                    int feno = getFeno(curUnit);

                    // PreparedStatement에 값 설정
                    pstmt.setBigDecimal(1, new BigDecimal(basicRate.replace(",", ""))); // 쉼표 제거 후 BigDecimal로 변환
                    pstmt.setDate(2, Date.valueOf(formattedDate)); // 고정된 날짜를 사용
                    pstmt.setInt(3, feno); // feno 값을 사용

                    // 데이터베이스에 데이터 삽입
                    pstmt.addBatch(); // 배치 작업에 추가
                }
            }

            // 모든 배치 작업을 실행
            pstmt.executeBatch();
            System.out.println("Data inserted successfully for date: " + formattedDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean isTargetCurrency(String curUnit) {
        for (String target : TARGET_CURRENCIES) {
            if (curUnit.equals(target)) {
                return true;
            }
        }
        return false;
    }

    private static int getFeno(String curUnit) {
        switch (curUnit) {
            case "CNH":
                return 1;
            case "EUR":
                return 2;
            case "GBP":
                return 3;
            case "JPY(100)":
                return 4;
            case "USD":
                return 5;
            default:
                return -1; // 매칭되지 않는 경우
        }
    }
}
