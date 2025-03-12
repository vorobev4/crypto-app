package com.vorobew4you.crypto.client;

import com.vorobew4you.crypto.config.IntegrationProperties;
import com.vorobew4you.crypto.dto.response.kucoin.KucoinResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class KucoinClient {

    private final RestTemplate template;
    private final IntegrationProperties properties;

    public ResponseEntity<KucoinResponse> getTickers() {
        String url = UriComponentsBuilder
                .fromHttpUrl(properties.getKucoinUrl())
                .toUriString();

        return template.exchange(url, HttpMethod.GET, null, KucoinResponse.class);
    }

}
