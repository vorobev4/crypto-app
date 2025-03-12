package com.vorobew4you.crypto.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "crypto-app.rest")
@Data
public class IntegrationProperties {
    private String binanceUrl;
    private String bybitUrl;
    private String kucoinUrl;
    private String mexcUrl;
}
