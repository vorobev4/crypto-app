package com.vorobew4you.crypto.dto.response.kucoin;

import org.springframework.lang.NonNull;

import java.util.List;

public record KucoinData(
    Long time,
    @NonNull
    List<KucoinDataTicker> ticker
) {
}
