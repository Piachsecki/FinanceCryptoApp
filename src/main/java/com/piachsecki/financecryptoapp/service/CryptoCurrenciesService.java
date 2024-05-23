package com.piachsecki.financecryptoapp.service;

import com.piachsecki.financecryptoapp.domain.CryptoCurrency;
import com.piachsecki.financecryptoapp.domain.CryptoCurrencyType;
import com.piachsecki.financecryptoapp.repository.CryptoRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class CryptoCurrenciesService {

    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final CryptoRepository cryptoRepository;
    private final RestTemplate restTemplate;

    @Value(value = "${stock_api_key}")
    private String apiKey;

    @Value("${CRYPTO_BASE_URL}")
    private String url;

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public void processRequests() {
        String formattedDate = LocalDate.now().minusDays(1).format(formatter);

        for (CryptoCurrencyType cryptoType : CryptoCurrencyType.values()) {
            scheduler.schedule(() -> {
                BigDecimal price = sendRequest(url, formattedDate, cryptoType.name());
                CryptoCurrency cryptoToAdd = CryptoCurrency.builder()
                        .cryptoCurrencyType(cryptoType)
                        .price(price)
                        .build();
                CryptoCurrency savedCrypto = cryptoRepository.save(cryptoToAdd);
                System.out.println("Saved Crypto: " + savedCrypto);
            }, getDelay(cryptoType.ordinal()), TimeUnit.SECONDS);
        }
    }

    private BigDecimal sendRequest(String url, String date, String cryptoType) {
        HttpHeaders headers = new HttpHeaders();
        url = url.replaceAll("CHANGE_CRYPTO", cryptoType);
        url = url.replace("CHANGE_DAY", date);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        final HttpEntity<String> headersEntity = new HttpEntity<>(headers);
        ResponseEntity<Map> exchange = restTemplate.exchange(
                url + "&apiKey=" + apiKey,
                HttpMethod.GET, headersEntity, Map.class
        );
        JSONObject jsonObject = new JSONObject(Objects.requireNonNull(exchange.getBody()));
        JSONArray jsonArray = jsonObject.getJSONArray("results");
        return BigDecimal.valueOf((Double) jsonArray.getJSONObject(0).get("o"));
    }

    private long getDelay(int index) {
        return index * 10L; // Delay each request by 10 seconds per index
    }
}
