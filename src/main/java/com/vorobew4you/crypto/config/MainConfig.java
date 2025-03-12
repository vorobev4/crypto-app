package com.vorobew4you.crypto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableJpaAuditing
public class MainConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
