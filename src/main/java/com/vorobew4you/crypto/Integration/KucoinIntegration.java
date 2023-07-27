package com.vorobew4you.crypto.Integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vorobew4you.crypto.Model.TradingPair;
import com.vorobew4you.crypto.Response.KucoinResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;

public class KucoinIntegration {
    private static final String GET_URL = "https://api.kucoin.com/api/v1/market/allTickers";
    private static final int PLATFORM_ID = 4;

    public static LinkedList<TradingPair> runIntegration() {
        try {
            return createObject(jsonMapper(sendGet()));
        } catch (Exception ex) {
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

    private static LinkedList<HashMap<String, String>> jsonMapper(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        KucoinResponse kucoinResponse = objectMapper.readValue(json, KucoinResponse.class);

        return kucoinResponse.getData().getTicker();
    }

    private static LinkedList<TradingPair> createObject(LinkedList<HashMap<String, String>> list) throws Exception {
        LinkedList<TradingPair> result = new LinkedList<>();

        for(HashMap<String, String> map : list) {
            TradingPair tradingPair = new TradingPair();

            if (!map.get("buy").equals("0")) {
                tradingPair.setName(map.get("symbol").replace("-", ""));
                tradingPair.setPlatformId(PLATFORM_ID);
                tradingPair.setPrice(Double.valueOf(map.get("last")));
                tradingPair.setUpdatedAt(LocalDateTime.now());

                result.add(tradingPair);
            }
        }

        return result;
    }
}
