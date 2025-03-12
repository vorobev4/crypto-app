package com.vorobew4you.crypto.dto.response.bybit;

import org.springframework.lang.NonNull;

public record BybitOrderbookResponse(
        Integer retCode,
        String retMsg,
        @NonNull
        BybitOrderbookResult result,
        BybitRetExtInfo retExtInfo,
        Long time
) {
}
