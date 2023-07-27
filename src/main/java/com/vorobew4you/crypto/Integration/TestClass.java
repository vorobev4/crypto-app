package com.vorobew4you.crypto.Integration;

import com.vorobew4you.crypto.Model.TokenName;
import com.vorobew4you.crypto.Repository.TokenNameRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TestClass {
    @Autowired
    private TokenNameRepository tokenNameRepository;

    public TestClass(TokenNameRepository tokenNameRepository) {
        this.tokenNameRepository = tokenNameRepository;
    }

    public void testMethod() {
        List<TokenName> token = tokenNameRepository.findAll();
        System.out.println(token.get(0).getName());

    }
}
