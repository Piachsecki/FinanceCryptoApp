package com.piachsecki.financecryptoapp;

import com.crazzyghost.alphavantage.AlphaVantage;
import com.crazzyghost.alphavantage.Config;
import com.crazzyghost.alphavantage.cryptocurrency.Crypto;
import com.crazzyghost.alphavantage.exchangerate.ExchangeRate;
import com.crazzyghost.alphavantage.parameters.Interval;
import com.crazzyghost.alphavantage.parameters.OutputSize;

import java.io.File;

public class CustomerWallet {


    public static void main(String[] args) {
        Config cfg = Config.builder()
                .key("DYXA0B2OXU0MNA7G")
                .timeOut(10)
                .build();

        AlphaVantage.api().init(cfg);
        AlphaVantage.api()
                .timeSeries()
                .intraday()
                .forSymbol("IBM")
                .interval(Interval.FIVE_MIN)
                .outputSize(OutputSize.FULL)
                .fetch();




        AlphaVantage.api().crypto().weekly().fetch();
//        System.out.println(crypto);
        System.out.println("################");
        System.out.println("################");
        System.out.println("################");
        System.out.println("################");
        System.out.println("################");
        System.out.println("################");
        System.out.println("################");
        System.out.println("################");
        System.out.println("################");
        System.out.println("################");
        System.out.println("################");
        System.out.println("################");
        System.out.println("################");
        Crypto exchangeRate = AlphaVantage.api().crypto();
        System.out.println(exchangeRate.toString());

    }
}
