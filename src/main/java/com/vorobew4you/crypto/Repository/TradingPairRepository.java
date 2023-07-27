package com.vorobew4you.crypto.Repository;

import com.vorobew4you.crypto.Model.TradingPair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.LinkedList;

public interface TradingPairRepository extends JpaRepository<TradingPair, Integer> {
    @Transactional
    @Modifying
    @Query("update TradingPair u set u.price = :price, u.updatedAt = :updatedAt where u.name = :name and u.platformId = :platformId")
    int updatePrice(@Param(value = "price") Double price, @Param(value = "updatedAt") LocalDateTime updatedAt,
                    @Param(value = "name") String name, @Param(value = "platformId") int platformId);

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

    @Transactional
    @Query("select u from TradingPair u where u.platformId = :platformId and (u.name like %:name% and u.name like %:secondName%)")
    LinkedList<TradingPair> findByPlatformIdLikeTwoName(
            @Param(value = "platformId") int platformId, @Param(value = "name") String name,
            @Param(value = "secondName") String secondName);

}
