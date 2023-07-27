package com.vorobew4you.crypto.Controller;

import com.vorobew4you.crypto.Integration.*;
import com.vorobew4you.crypto.Model.CryptoPortal;
import com.vorobew4you.crypto.Model.ResultView;
import com.vorobew4you.crypto.Model.TokenName;
import com.vorobew4you.crypto.Model.TradingPair;
import com.vorobew4you.crypto.Repository.CryptoPortalRepository;
import com.vorobew4you.crypto.Repository.ResultViewRepository;
import com.vorobew4you.crypto.Repository.TokenNameRepository;
import com.vorobew4you.crypto.Repository.TradingPairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;

@RestController
public class TradingPairController {
    @Autowired
    private TradingPairRepository tradingPairRepository;
    @Autowired
    private CryptoPortalRepository cryptoPortalRepository;
    @Autowired
    private ResultViewRepository resultViewRepository;
    @Autowired
    private TokenNameRepository tokenNameRepository;

    @PostMapping("/api/test")
    public String runTest() {
        TestClass testClass = new TestClass(tokenNameRepository);
//        List<TokenName> token = tokenNameRepository.findAll();
//        System.out.println(token.get(0).getName());

        testClass.testMethod();

        return null;
    }

    @PostMapping("/api/v1/run/binance")
    public String runBinance() {
        try {
            Instant start = Instant.now();
            LinkedList<TradingPair> list = BinanceIntegration.runIntegration();

            for (TradingPair tradingPair : list) {
                try {
                    tradingPairRepository.save(tradingPair);
                } catch (Exception ex) {
                    tradingPairRepository.updatePrice(
                            tradingPair.getPrice(), tradingPair.getUpdatedAt(),
                            tradingPair.getName(), tradingPair.getPlatformId()
                    );
                }
            }

            Instant stop = Instant.now();
            return "Success: "
                    + Duration.between(start, stop).toMinutes() + ":"
                    + Duration.between(start, stop).toSeconds();
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Error: " + ex.getMessage();
        }
    }

    @PostMapping("/api/v1/run/bybit")
    public String runBybit() {
        try {
            Instant start = Instant.now();
            LinkedList<TradingPair> list = BybitIntegration.runIntegration(tradingPairRepository.getDistinctName());

            for (TradingPair tradingPair : list) {
                try {
                    tradingPairRepository.save(tradingPair);
                } catch (Exception ex) {
                    tradingPairRepository.updatePrice(
                            tradingPair.getPrice(), tradingPair.getUpdatedAt(),
                            tradingPair.getName(), tradingPair.getPlatformId()
                    );
                }
            }

            Instant stop = Instant.now();
            return "Success: "
                    + Duration.between(start, stop).toMinutes() + ":"
                    + Duration.between(start, stop).toSeconds();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

    @PostMapping("/api/v1/run/mexc")
    public String runMexc() {
        try {
            Instant start = Instant.now();
            LinkedList<TradingPair> list = MexcIntegration.runIntegration();

            for (TradingPair tradingPair : list) {
                try {
                    tradingPairRepository.save(tradingPair);
                } catch (Exception ex) {
                    tradingPairRepository.updatePrice(
                            tradingPair.getPrice(), tradingPair.getUpdatedAt(),
                            tradingPair.getName(), tradingPair.getPlatformId()
                    );
                }
            }

            Instant stop = Instant.now();
            return "Success: "
                    + Duration.between(start, stop).toMinutes() + ":"
                    + Duration.between(start, stop).toSeconds();
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Error: " + ex.getMessage();
        }
    }

    @PostMapping("/api/v1/run/kucoin")
    public String runKucoin() {
        try {
            Instant start = Instant.now();
            LinkedList<TradingPair> list = KucoinIntegration.runIntegration();

            for (TradingPair tradingPair : list) {
                try {
                    tradingPairRepository.save(tradingPair);
                } catch (Exception ex) {
                    tradingPairRepository.updatePrice(
                            tradingPair.getPrice(), tradingPair.getUpdatedAt(),
                            tradingPair.getName(), tradingPair.getPlatformId()
                    );
                }
            }

            Instant stop = Instant.now();
            return "Success: "
                    + Duration.between(start, stop).toMinutes() + ":"
                    + Duration.between(start, stop).toSeconds();
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Error: " + ex.getMessage();
        }
    }

    @PostMapping("/api/v1/set/token/name")
    public String setTokenName() {
        try {
            for (TokenName token : SetTokenName.getTokenName()) {
                try {
                    tokenNameRepository.save(token);
                } catch (Exception ex) {}
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @PostMapping("/api/v1/run/comparison")
    public String runPriceComparison() {
        try {
            Instant start = Instant.now();

            LinkedList<LinkedList<TradingPair>> tradingPairList = new LinkedList<LinkedList<TradingPair>>();
            Iterable<CryptoPortal> cryptoPortal = cryptoPortalRepository.findAll();
//            int cryptoPortalCount = (int) cryptoPortalRepository.count();

            for (CryptoPortal cp : cryptoPortal) {
                tradingPairList.add(tradingPairRepository.findByPlatformId(cp.getId()));
            }

            LinkedList<ResultView> resultViewList = PriceComparison.runPriceComparison(tradingPairList);

            if (resultViewList != null && !resultViewList.isEmpty()) {
                resultViewRepository.deleteAll();
                for(ResultView resultView : resultViewList) {
//                    System.out.println(resultView.getPlatformName());
                    resultViewRepository.save(resultView);
                }
            }

//            ObjectMapper mapper = new ObjectMapper();
//            ArrayNode arrayNode = mapper.createArrayNode();
//
//            List<ResultView> resultList = resultViewRepository.findAll();
//            for (ResultView resultView : resultList) {
//                ObjectNode objectNode = mapper.createObjectNode();
//                objectNode.put("pair_name", resultView.getPairName());
//                objectNode.put("platform_name", resultView.getPlatformName());

//                arrayNode.add(objectNode);
//            }

            Instant stop = Instant.now();
            return "Success: "
                    + Duration.between(start, stop).toMinutes() + ":"
                    + Duration.between(start, stop).toSeconds();

            //Настроить ответ при ерроре через ObjectNode
            // указать в возвращаемых дынных указать обединяющий их интерфейс или класс
//            return arrayNode;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
//            return null;
        }
    }

    @PostMapping("/api/v1/run/search_in_portal/{portalId}")
    public String runSearchInPortal(@PathVariable(value="portalId") int portalId) {
        try {
            Instant start = Instant.now();

            SearchInPortal searchInPortal = new SearchInPortal(tokenNameRepository, tradingPairRepository);
            searchInPortal.runSearch(portalId);

            Instant stop = Instant.now();
            return "Success: "
                    + Duration.between(start, stop).toMinutes() + ":"
                    + Duration.between(start, stop).toSeconds();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
}
