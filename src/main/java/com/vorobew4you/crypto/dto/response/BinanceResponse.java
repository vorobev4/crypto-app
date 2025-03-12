package com.vorobew4you.crypto.dto.response;

import org.springframework.lang.NonNull;

import java.math.BigDecimal;

public record BinanceResponse(
        @NonNull
        String symbol,
        BigDecimal bidPrice,
        BigDecimal bidQty,
        BigDecimal askPrice,
        BigDecimal askQty
) {
}
