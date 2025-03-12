package com.vorobew4you.crypto.client;

import com.vorobew4you.crypto.config.IntegrationProperties;
import com.vorobew4you.crypto.dto.response.bybit.BybitOrderbookResponse;
import com.vorobew4you.crypto.dto.response.bybit.BybitTicketResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class BybitClient {

    private final RestTemplate template;
    private final IntegrationProperties properties;

    public ResponseEntity<BybitTicketResponse> getTickers() {
        String url = UriComponentsBuilder
                .fromHttpUrl(properties.getBybitUrl() + "/tickers?category=spot")
                .toUriString();

        return template.exchange(url, HttpMethod.GET, null, BybitTicketResponse.class);
    }

    public ResponseEntity<BybitOrderbookResponse> getOrderbook(String symbol) {
        String url = UriComponentsBuilder
                .fromHttpUrl(properties.getBybitUrl() + "/orderbook?category=spot&symbol=" + symbol)
                .toUriString();

        return template.exchange(url, HttpMethod.GET, null, BybitOrderbookResponse.class);
    }

}
