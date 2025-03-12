package com.vorobew4you.crypto.dto.response.kucoin;

import org.springframework.lang.NonNull;

public record KucoinResponse(
        String code,
        @NonNull
        KucoinData data
) {
}
//todo настроить нормальную проверку на null что бы в stream api не было такого что поля с тикетами нет