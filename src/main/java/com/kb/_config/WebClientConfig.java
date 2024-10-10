package com.kb._config;

import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import io.netty.handler.ssl.SslContextBuilder;

import javax.net.ssl.SSLException;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient() {
        // SSL 설정을 비활성화하여 모든 인증서를 신뢰하도록 구성
        HttpClient httpClient = HttpClient.create()
                .secure(sslSpec -> {
                    try {
                        sslSpec.sslContext(SslContextBuilder.forClient()
                                .trustManager(InsecureTrustManagerFactory.INSTANCE)  // 모든 인증서 신뢰
                                .build());
                    } catch (SSLException e) {
                        e.printStackTrace();
                    }
                });

        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }

}
