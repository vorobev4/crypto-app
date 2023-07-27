package com.vorobew4you.crypto.Model;

import jakarta.persistence.*;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
public class ResultView {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String pairName;

    private String platformName; // "name1/name2"

    private Double firstPrice;

    private Double secondPrice;

    private Double priceDifference;

    private Double percentPriceDifference;

    @LastModifiedDate
    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    public Integer getId() {
        return id;
    }

    public String getPairName() {
        return pairName;
    }

    public void setPairName(String pairName) {
        this.pairName = pairName;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public Double getFirstPrice() {
        return firstPrice;
    }

    public void setFirstPrice(Double firstPrice) {
        this.firstPrice = firstPrice;
    }

    public Double getSecondPrice() {
        return secondPrice;
    }

    public void setSecondPrice(Double secondPrice) {
        this.secondPrice = secondPrice;
    }

    public Double getPriceDifference() {
        return priceDifference;
    }

    public void setPriceDifference(Double priceDifference) {
        this.priceDifference = priceDifference;
    }

    public Double getPercentPriceDifference() {
        return percentPriceDifference;
    }

    public void setPercentPriceDifference(Double percentPriceDifference) {
        this.percentPriceDifference = percentPriceDifference;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
