package com.vorobew4you.crypto.service;

import com.vorobew4you.crypto.client.BinanceClient;
import com.vorobew4you.crypto.client.BybitClient;
import com.vorobew4you.crypto.client.KucoinClient;
import com.vorobew4you.crypto.client.MexcClient;
import com.vorobew4you.crypto.model.PlatformIdEnum;
import com.vorobew4you.crypto.model.TradingPair;
import com.vorobew4you.crypto.repository.TradingPairRepository;
import com.vorobew4you.crypto.service.mapper.TradingPairMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class IntegrationService {

    @Autowired
    private final TradingPairRepository tradingPairRepository;
    private final BinanceClient binanceClient;
    private final BybitClient bybitClient;
    private final KucoinClient kucoinClient;
    private final MexcClient mexcClient;
    private final TradingPairMapper tradingPairMapper;

    //todo сделать нормальную проверку полученных данных
    //todo разобраться с upsert для нормального процесса добавления/обновления данных
    //todo допилить запись данных в бд по возможности ускорить,
    // возможно лучше использовать получение нужных данных из бд и после их искать и выполнять upsert
    // непосредственно на бэке что бы не загружать бд и не тратить время на запрос каждого из объектов
    // возможно есть хорошие практики в этом направлении и это сможет заставить работать авто обновление даты
    //todo разобраться с @Transactional и @EnableTransactionManagement как их правильно и эффективно использовать
    public ResponseEntity<Void> getAllData() {
//        getBinanceData();
//        getBybitData();
//        getKuCoinData();
//        getMexcData();

//        var x = tradingPairRepository.findPrice();

        return ResponseEntity.status(200).build();
    }

    //todo подумать о переносе стрима из метода get...Data в метод saveOrUpdate
    private void getBinanceData() {
        var x = binanceClient.getTickers().getBody();
        if (x != null) {
            var c = x.stream()
                    .map(e -> tradingPairMapper.toTradingPair(e, PlatformIdEnum.BINANCE))
                    .filter(e -> e.getPrice() != null)
                    .toList();

            for (TradingPair tp : c) {
                saveOrUpdate(tp);
            }
        }
    }

    private void getBybitData() {
        var x = bybitClient.getTickers().getBody();
        if (x != null) {
            var c = x.result().list().stream()
                    .map(e -> tradingPairMapper.toTradingPair(e, PlatformIdEnum.BYBIT))
                    .filter(e -> e.getPrice() != null)
                    .toList();

            for (TradingPair tp : c) {
                saveOrUpdate(tp);
            }
        }
    }

    private void getKuCoinData() {
        var x = kucoinClient.getTickers().getBody();
        if (x != null) {
            var c = x.data().ticker().stream()
                    .map(e -> tradingPairMapper.toTradingPair(e, PlatformIdEnum.KUCOIN))
                    .filter(e -> e.getPrice() != null)
                    .toList();

            for (TradingPair tp : c) {
                saveOrUpdate(tp);
            }
        }
    }

    private void getMexcData() {
        var x = mexcClient.getTickers().getBody();
        if (x != null) {
            var c = x.stream()
                    .map(e -> tradingPairMapper.toTradingPair(e, PlatformIdEnum.MEXC))
                    .filter(e -> e.getPrice() != null)
                    .toList();

            for (TradingPair tp : c) {
                saveOrUpdate(tp);
            }
        }
    }

    @Transactional
    private void saveOrUpdate(TradingPair tp) {
        TradingPair pait = tradingPairRepository.findByPlatformIdAndName(tp.getPlatformId(), tp.getName());

        if (pait == null) {
            tradingPairRepository.save(tp);
        } else {
            if (!pait.getPrice().equals(tp.getPrice()))
                tradingPairRepository.updatePriceById(tp.getPrice(), Instant.now(), pait.getId());
        }
    }
}
