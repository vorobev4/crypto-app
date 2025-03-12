package com.vorobew4you.crypto.client;

import com.vorobew4you.crypto.config.IntegrationProperties;
import com.vorobew4you.crypto.dto.response.MexcResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MexcClient {

    private final RestTemplate template;
    private final IntegrationProperties properties;

    public ResponseEntity<List<MexcResponse>> getTickers() {
        String url = UriComponentsBuilder
                .fromHttpUrl(properties.getMexcUrl())
                .toUriString();

        return template.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<MexcResponse>>() {});
    }

}
