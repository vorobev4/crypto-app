package com.vorobew4you.crypto.service.mapper;

import com.vorobew4you.crypto.dto.response.MexcResponse;
import com.vorobew4you.crypto.dto.response.kucoin.KucoinDataTicker;
import com.vorobew4you.crypto.model.PlatformIdEnum;
import com.vorobew4you.crypto.dto.response.BinanceResponse;
import com.vorobew4you.crypto.dto.response.bybit.BybitTickersResultList;
import com.vorobew4you.crypto.model.TradingPair;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = TradingPairHelperMapper.class)
public interface TradingPairMapper {

    @Mapping(target = "platformId", source = "platformIdEnum", qualifiedByName = "getPlatformIdEnum")
    @Mapping(target = "name", source = "binanceResponse.symbol")
    @Mapping(target = "price", source = "binanceResponse.bidPrice")
    TradingPair toTradingPair(BinanceResponse binanceResponse, PlatformIdEnum platformIdEnum);

    @Mapping(target = "platformId", source = "platformIdEnum", qualifiedByName = "getPlatformIdEnum")
    @Mapping(target = "name", source = "bybitTickersResultList.symbol")
    @Mapping(target = "price", source = "bybitTickersResultList.bid1Price")
    TradingPair toTradingPair(BybitTickersResultList bybitTickersResultList, PlatformIdEnum platformIdEnum);

    @Mapping(target = "platformId", source = "platformIdEnum", qualifiedByName = "getPlatformIdEnum")
    @Mapping(target = "name", source = "kucoinDataTicker.symbol", qualifiedByName = "fixedSymbol")
    @Mapping(target = "price", source = "kucoinDataTicker.sell")
    TradingPair toTradingPair(KucoinDataTicker kucoinDataTicker, PlatformIdEnum platformIdEnum);

    @Mapping(target = "platformId", source = "platformIdEnum", qualifiedByName = "getPlatformIdEnum")
    @Mapping(target = "name", source = "mexcResponse.symbol")
    @Mapping(target = "price", source = "mexcResponse.bidPrice")
    TradingPair toTradingPair(MexcResponse mexcResponse, PlatformIdEnum platformIdEnum);

}
