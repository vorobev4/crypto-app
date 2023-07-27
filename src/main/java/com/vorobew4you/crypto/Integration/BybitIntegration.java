package com.vorobew4you.crypto.Integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vorobew4you.crypto.Model.TradingPair;
import com.vorobew4you.crypto.Response.BybitResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.LinkedList;

public class BybitIntegration {
    private static final String GET_URL = "https://api.bybit.com/v5/market/orderbook?category=spot&symbol=";
    private static final int PLATFORM_ID = 2;

    public static LinkedList<TradingPair> runIntegration(LinkedList<String> list) {
        LinkedList<TradingPair> tradingPairsList = new LinkedList<TradingPair>();

        try {
            for (String str : list) {
                TradingPair tradingPair = jsonMapper(sendGET(str));

                if (tradingPair != null) {
                    tradingPairsList.add(tradingPair);
                }
            }

            return tradingPairsList;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private static String sendGET(String symbol) throws IOException {
        URL obj = new URL(GET_URL + symbol);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
//        System.out.println("GET Response Code :: " + responseCode);
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

    // Разделить метод на метод отвечающий за маппинг и метод создания объекта
    private static TradingPair jsonMapper(String json) throws Exception {
        try {
            TradingPair tradingPair = new TradingPair();
            ObjectMapper objectMapper = new ObjectMapper();
            BybitResponse bybitResponses = objectMapper.readValue(json, BybitResponse.class);

            String name = bybitResponses.getResult().getS();
            Double price = bybitResponses.getResult().getA().get(0).get(0);

            if (price != null && name != null) {
                tradingPair.setPlatformId(PLATFORM_ID);
                tradingPair.setName(name);
                tradingPair.setPrice(price);
                tradingPair.setUpdatedAt(LocalDateTime.now());
            } else {
                return null;
            }
            return tradingPair;
        } catch (Exception ex) {
            return null;
        }
    }
}
