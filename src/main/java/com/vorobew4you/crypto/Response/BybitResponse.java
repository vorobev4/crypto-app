package com.vorobew4you.crypto.Response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedList;

public class BybitResponse {
    @JsonIgnore
    @JsonProperty("retCode")
    private int retCode;

    @JsonIgnore
    @JsonProperty("retMsg")
    private String retMsg;

    @JsonIgnore
    @JsonProperty("retExtInfo")
    private Object retExtInfo;

    @JsonIgnore
    @JsonProperty("time")
    private long time;

    @JsonProperty("result")
    private BybitResponseResult result;

    public static class BybitResponseResult {
        @JsonProperty("s")
        private String s;

        @JsonProperty("a")
        private LinkedList<LinkedList<Double>> a;

        @JsonIgnore
        @JsonProperty("b")
        private Object b;

        @JsonIgnore
        @JsonProperty("ts")
        private long ts;

        @JsonIgnore
        @JsonProperty("u")
        private long u;

        public String getS() {
            return s;
        }

        public LinkedList<LinkedList<Double>> getA() {
            return a;
        }
    }

    public BybitResponseResult getResult() {
        return result;
    }
}

