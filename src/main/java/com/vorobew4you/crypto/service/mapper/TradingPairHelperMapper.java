package com.vorobew4you.crypto.service.mapper;

import com.vorobew4you.crypto.model.PlatformIdEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface TradingPairHelperMapper {
    @Named("getPlatformIdEnum")
    default Integer getPlatformIdEnum(PlatformIdEnum platformIdEnum) {
        return platformIdEnum.getPlatformId();
    }

    @Named("fixedSymbol")
    default String fixedSymbol(String symbol) {
        return symbol.replace("-", "");
    }
}
