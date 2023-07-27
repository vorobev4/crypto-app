package com.vorobew4you.crypto.Repository;

import com.vorobew4you.crypto.Model.TokenName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

public interface TokenNameRepository extends JpaRepository<TokenName, Integer> {
    @Transactional
    @Query("select distinct(u.name) from TokenName u")
    Set<String> getDistinctName();

}
