package com.vorobew4you.crypto.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "result_view")
public class ResultView extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String pairName;

    private String platformName; // "name1/name2"

    private BigDecimal firstPrice;

    private BigDecimal secondPrice;

    private BigDecimal priceDifference;

    private BigDecimal percentPriceDifference;
}
