package com.vorobew4you.crypto.dto.response.bybit;

public record BybitTickersResultList(
        String symbol,
        String bid1Price,
        String bid1Size,
        String ask1Price,
        String ask1Size,
        String lastPrice,
        String prevPrice24h,
        String price24hPcnt,
        String highPrice24h,
        String lowPrice24h,
        String turnover24h,
        String volume24h
) {
}
