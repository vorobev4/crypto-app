package com.vorobew4you.crypto.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "trading_pair", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"platformId", "name"})
})
public class TradingPair extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Integer platformId;

    @Column(nullable = false)
    private String name;

    @Column(name = "price", nullable = false, precision = 20, scale = 8)
    private BigDecimal price;
}
