package com.vorobew4you.crypto.dto.response.bybit;

import org.springframework.lang.NonNull;

import java.util.List;

public record BybitTickersResult(
        String category,
        @NonNull
        List<BybitTickersResultList> list
) {
}
