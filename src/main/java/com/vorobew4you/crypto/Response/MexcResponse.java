package com.vorobew4you.crypto.Response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MexcResponse {
    @JsonProperty("symbol")
    private String symbol;
    @JsonProperty("bidPrice")
    private Double bidPrice;
    @JsonProperty("bidQty")
    private Double bidQty;
    @JsonProperty("askPrice")
    private Double askPrice;
    @JsonProperty("askQty")
    private Double askQty;

    public String getSymbol() {
        return symbol;
    }

    public Double getBidPrice() {
        return bidPrice;
    }
}
