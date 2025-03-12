package com.vorobew4you.crypto.model;

import lombok.Getter;

import java.io.Serializable;

@Getter
public enum PlatformIdEnum implements Serializable {
    BINANCE(1),
    BYBIT(2),
    KUCOIN(3),
    MEXC(4);

    private final Integer platformId;

    PlatformIdEnum(Integer platformId) {
        this.platformId = platformId;
    }
}
