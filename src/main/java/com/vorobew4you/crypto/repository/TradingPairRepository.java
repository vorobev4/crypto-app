package com.vorobew4you.crypto.repository;

import com.vorobew4you.crypto.model.TradingPair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.LinkedList;

@Repository
public interface TradingPairRepository extends JpaRepository<TradingPair, Long> {
    @Transactional
    @Modifying
    @Query("update TradingPair u set u.price = :price, u.lastModifiedDate = :lastModifiedDate where u.name = :name and u.platformId = :platformId")
    int updatePrice(@Param(value = "price") BigDecimal price,
                    @Param(value = "lastModifiedDate") Instant lastModifiedDate,
                    @Param(value = "name") String name, @Param(value = "platformId") int platformId);

    @Transactional
    @Modifying
    @Query("update TradingPair t set t.price = :price, t.lastModifiedDate = :lastModifiedDate where t.id = :id")
    int updatePriceById(@Param(value = "price") BigDecimal price,
                        @Param(value = "lastModifiedDate") Instant lastModifiedDate,
                        @Param(value = "id") Long id);

    @Transactional
    @Query("select distinct(u.name) from TradingPair u")
    LinkedList<String> getDistinctName();

    @Transactional
    @Query("select u from TradingPair u where u.platformId = :platformId and u.price <> 0")
    LinkedList<TradingPair> findByPlatformId(@Param(value = "platformId") int platformId);

    @Transactional
    @Query("select u from TradingPair u where u.platformId = :platformId and u.name like %:name%")
    LinkedList<TradingPair> findByPlatformIdLikeName(
            @Param(value = "platformId") int platformId, @Param(value = "name") String name);

    TradingPair findByPlatformIdAndName(
            @Param(value = "platformId") int platformId, @Param(value = "name") String name);

    @Transactional
    @Query("select u from TradingPair u where u.platformId = :platformId and (u.name like %:name% and u.name like %:secondName%)")
    LinkedList<TradingPair> findByPlatformIdLikeTwoName(
            @Param(value = "platformId") int platformId, @Param(value = "name") String name,
            @Param(value = "secondName") String secondName);

//    @Transactional
//    @Query("SELECT t.platform_id, t.name, t.price, p.platform_id, p.name, p.price, t.price - p.price AS result " +
//            "FROM trading_pair AS t" +
//            "JOIN trading_pair AS p" +
//            "ON t.platform_id != p.platform_id" +
//            "AND t.name = p.name" +
//            "WHERE t.price != 0.00000000 AND p.price != 0.00000000")
//    int findPrice();
}
