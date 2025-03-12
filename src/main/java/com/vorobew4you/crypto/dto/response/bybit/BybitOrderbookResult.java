package com.vorobew4you.crypto.dto.response.bybit;

import java.util.List;

public record BybitOrderbookResult(
        String s,
        List<List<String>> a,
        List<List<String>> b,
        Long ts,
        Integer u,
        Long seq,
        Long cts
) {
}
