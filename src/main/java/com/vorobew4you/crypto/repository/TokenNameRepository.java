package com.vorobew4you.crypto.repository;

import com.vorobew4you.crypto.model.TokenName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

public interface TokenNameRepository extends JpaRepository<TokenName, Long> {
    @Transactional
    @Query("select distinct(u.name) from TokenName u")
    Set<String> getDistinctName();

}
