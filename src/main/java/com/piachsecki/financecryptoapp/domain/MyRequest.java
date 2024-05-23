package com.piachsecki.financecryptoapp.domain;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Getter
public class MyRequest {

    @Value("${CRYPTO_BASE_URL}")
    private String url;

    public MyRequest(String cryptoCurrencyType, String date){
        this.url = this.url.replaceAll("CHANGE_CRYPTO", cryptoCurrencyType);
        this.url = this.url.replace("CHANGE_DAY", date);
    }
}
