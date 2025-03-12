package com.vorobew4you.crypto.dto.response.kucoin;

public record KucoinDataTicker(
        String symbol,
        String symbolName,
        String buy,
        String bestBidSize,
        String sell,
        String bestAskSize,
        String changeRate,
        String changePrice,
        String high,
        String low,
        String vol,
        String volValue,
        String last,
        String averagePrice,
        String takerFeeRate,
        String makerFeeRate,
        String takerCoefficient,
        String makerCoefficient
) {
}
