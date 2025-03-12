package com.vorobew4you.crypto.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CryptoPortalController {
//    @Autowired
//    private CryptoPortalRepository cryptoPortalRepository;
//
//    @Autowired
//    private TradingPairRepository tradingPairRepository;


    @PostMapping("/")
    public String index() {
        return "hello";
    }

//    @GetMapping("/api/v1/portal/all")
//    public @ResponseBody Iterable<CryptoPortal> getAllPortal() {
//        return cryptoPortalRepository.findAll();
//    }
}
