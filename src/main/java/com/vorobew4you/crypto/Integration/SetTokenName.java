package com.vorobew4you.crypto.Integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vorobew4you.crypto.Model.TokenName;
import com.vorobew4you.crypto.Response.KucoinResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class SetTokenName {
    private static final String GET_URL = "https://api.kucoin.com/api/v1/market/allTickers";

    public static LinkedList<TokenName> getTokenName() {
        LinkedList<TokenName> list = new LinkedList<TokenName>();

        try {
            HashSet<String> set = createObject(jsonMapper(sendGet()));

            for (String str : set) {
                TokenName tokenName = new TokenName();
                tokenName.setName(str);

                list.add(tokenName);
            }

            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return list;
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

    private static HashSet<String> createObject(LinkedList<HashMap<String, String>> list) throws Exception {
        LinkedList<TokenName> result = new LinkedList<>();
        HashSet<String> set = new HashSet<String>();

        for(HashMap<String, String> map : list) {
            String[] arr = map.get("symbol").split("-");
            set.addAll(Arrays.asList(arr));
        }

        return set;
    }

}
