package com.vorobew4you.crypto.Integration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vorobew4you.crypto.Response.BinanceResponse;
import com.vorobew4you.crypto.Model.TradingPair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.LinkedList;

public class BinanceIntegration {
    private static final String GET_URL = "https://api.binance.com/api/v3/ticker/bookTicker";
    private static final int PLATFORM_ID = 1;

    public static LinkedList<TradingPair> runIntegration() {
        try {
            return createObject(jsonMapper(sendGet()));
        } catch (Exception ex) {
            ex.printStackTrace();

            return null;
        }
    }

    private static String sendGet() throws IOException {
        URL obj = new URL(GET_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
//            System.out.println(response.toString());

            return response.toString();
        } else {
            System.out.println("GET request did not work.");

            return null;
        }
    }

    private static LinkedList<BinanceResponse> jsonMapper(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        if (json.subSequence(0, 1).equals("[")) {
            LinkedList<BinanceResponse> binanceList = objectMapper.readValue(json, new TypeReference<LinkedList<BinanceResponse>>() {});

            return binanceList;
        } else {
            System.out.println("Binance response is not array");

            return null;
        }
    }

    private static LinkedList<TradingPair> createObject(LinkedList<BinanceResponse> list) {
        LinkedList<TradingPair> tradingPairsList = new LinkedList<>();

        for (BinanceResponse response : list) {
            TradingPair tradingPair = new TradingPair();
            tradingPair.setPlatformId(PLATFORM_ID);
            tradingPair.setName(response.getSymbol());
            tradingPair.setPrice(response.getBidPrice());
            tradingPair.setUpdatedAt(LocalDateTime.now());

            tradingPairsList.add(tradingPair);
        }

        return tradingPairsList;
    }
}
