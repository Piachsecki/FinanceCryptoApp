package com.piachsecki.financecryptoapp.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    private HttpComponentsClientHttpRequestFactory getClientHttpRequestFactory() {

        // Create an instance of Apache HttpClient
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory
                = new HttpComponentsClientHttpRequestFactory();

        int connectTimeout = 5000;

        clientHttpRequestFactory.setConnectTimeout(connectTimeout);

        return clientHttpRequestFactory;
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }
}
