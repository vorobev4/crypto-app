package com.vorobew4you.crypto.Response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.LinkedList;

public class KucoinResponse {
    @JsonIgnore
    @JsonProperty("code")
    private String code;

    @JsonProperty("data")
    private KucoinResponseData data;

    public static class KucoinResponseData {
        @JsonIgnore
        @JsonProperty("time")
        private long time;

        @JsonProperty("ticker")
        private LinkedList<HashMap<String, String>> ticker;

        public LinkedList<HashMap<String, String>> getTicker() {
            return ticker;
        }
    }

    public KucoinResponseData getData() {
        return data;
    }
}
