package com.vorobew4you.crypto.dto.response.bybit;

import org.springframework.lang.NonNull;

public record BybitTicketResponse(
        Integer retCode,
        String retMsg,
        @NonNull
        BybitTickersResult result,
        BybitRetExtInfo retExtInfo,
        Long time
) {
}
