package com.vorobew4you.crypto.Integration;

import com.vorobew4you.crypto.Controller.TradingPairController;
import com.vorobew4you.crypto.Model.ResultView;
import com.vorobew4you.crypto.Model.TradingPair;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class PriceComparison {
    private static LinkedList<ResultView> resultViewsList;

    public static LinkedList<ResultView> runPriceComparison(LinkedList<LinkedList<TradingPair>> list) {
        try {
            resultViewsList = new LinkedList<ResultView>();
            for (int i = 0; i < list.size() - 1; i++) {
                LinkedList<TradingPair> tradingPairsList = new LinkedList<TradingPair>(list.getFirst());
                list.removeFirst();
                for (int j = 0; j < list.size(); j++) {
                    priceComparison(tradingPairsList, list.get(j));
                }
            }

            return resultViewsList;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static void priceComparison(LinkedList<TradingPair> first, LinkedList<TradingPair> second) throws Exception {
        for (TradingPair tpFirst : first) {
            for (TradingPair tpSecond: second) {
                ResultView resultView = new ResultView();
                TradingPairController tpc = new TradingPairController();
                if (tpFirst.getName().equals(tpSecond.getName())) {
                    Double firstPrice = tpFirst.getPrice();
                    Double secondPrice = tpSecond.getPrice();

                    resultView.setPriceDifference(Math.abs(firstPrice - secondPrice));
                    resultView.setPercentPriceDifference(Math.abs(
                            ((firstPrice - secondPrice) / secondPrice) * 100
                    ));


                    resultView.setPlatformName(
                            tpFirst.getPlatformId() + "/" + tpSecond.getPlatformId()
                    );
                    resultView.setPairName(tpFirst.getName());
                    resultView.setFirstPrice(firstPrice);
                    resultView.setSecondPrice(secondPrice);
                    resultView.setUpdatedAt(LocalDateTime.now());

                    resultViewsList.add(resultView);
                }
            }
        }
    }

}
