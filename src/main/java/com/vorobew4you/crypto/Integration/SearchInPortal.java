package com.vorobew4you.crypto.Integration;

import com.vorobew4you.crypto.Model.TokenName;
import com.vorobew4you.crypto.Model.TradingPair;
import com.vorobew4you.crypto.Repository.TokenNameRepository;
import com.vorobew4you.crypto.Repository.TradingPairRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class SearchInPortal {
    private final TokenNameRepository tokenNameRepository;
    private final TradingPairRepository tradingPairRepository;
    private Set<String> tokenNameSet;
    private LinkedList<TradingPair> tradingPairList;
    private int platformId;

    public SearchInPortal(TokenNameRepository tokenNameRepository, TradingPairRepository tradingPairRepository) {
        this.tokenNameRepository = tokenNameRepository;
        this.tradingPairRepository = tradingPairRepository;
    }

    private void setTokenNameSet() {
        this.tokenNameSet = tokenNameRepository.getDistinctName();
    }

    private void setTradingPairList(int portalId) {
        this.tradingPairList = tradingPairRepository.findByPlatformId(portalId);
    }

    private void setPlatformId(int platformId) {
        this.platformId = platformId;
    }

    public void runSearch(int portalId) {
        setTokenNameSet();
        setTradingPairList(portalId);
        setPlatformId(portalId);
        getAllTriangle();
//        System.out.println(Arrays.toString(tokenNameSet.stream().toArray()));

    }

    private void getAllTriangle() {
        for (String str : this.tokenNameSet) {
            str = "LTC";

            System.out.println(str);
            TradingPair[] tpArr = new TradingPair[3];
            LinkedList<TradingPair> list = tradingPairRepository.findByPlatformIdLikeName(this.platformId,str);

            if (list.size() > 2) {
                for (TradingPair tp : list) {
                    System.out.println("1 - " + tp.getName());
                    String firstPairName = tp.getName();
                    String tokenName = firstPairName.replace(str, "");

                    for(TradingPair tp1 : tradingPairRepository.findByPlatformIdLikeName(this.platformId, tokenName)) {
                        try {
                            System.out.println("2 - " + tp1.getName());
                            String secondPairName = tp1.getName();

                            LinkedList<TradingPair> tp2 = tradingPairRepository.findByPlatformIdLikeTwoName(
                                    this.platformId,
                                    firstPairName.replace(tokenName, ""),
                                    secondPairName.replace(tokenName, "")
                            );

                            if (tp2.size() > 0) {
                                System.out.println(tp2.size());
                                System.out.println(tp);
                                System.out.println(tp1);
                                System.out.println(tp2.getFirst());

                                System.out.println(priceDivision(tp.getPrice(), tp1.getPrice()));
                                System.out.println(priceDivision(tp.getPrice(), tp2.getFirst().getPrice()));
                                System.out.println(priceDivision(tp1.getPrice(), tp2.getFirst().getPrice()));
                            }
                        } catch (Exception e) {}

//                        break;
                    }

                    break;
                }
            }

            break;
        }
    }

    private Double priceDivision(Double a, Double b) {
        if (a < b) {
            return a / b;
        } else {
            return b / a;
        }
    }
}
