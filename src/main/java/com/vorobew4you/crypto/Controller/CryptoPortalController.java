package com.vorobew4you.crypto.Controller;

import com.vorobew4you.crypto.Integration.BinanceIntegration;
import com.vorobew4you.crypto.Model.CryptoPortal;
import com.vorobew4you.crypto.Repository.CryptoPortalRepository;
import com.vorobew4you.crypto.Repository.TradingPairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CryptoPortalController {
    @Autowired
    private CryptoPortalRepository cryptoPortalRepository;

    @Autowired
    private TradingPairRepository tradingPairRepository;


    @PostMapping("/")
    public String index() {
        return "hello";
    }

    @GetMapping("/api/v1/portal/all")
    public @ResponseBody Iterable<CryptoPortal> getAllPortal() {
        return cryptoPortalRepository.findAll();
    }
}
