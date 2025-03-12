package com.vorobew4you.crypto.rest;

import com.vorobew4you.crypto.service.IntegrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/")
@RestController
public class IntegrationController {
    private final IntegrationService service;

    @PostMapping("/getAllData")
    public ResponseEntity<Void> getAllData() {
        return service.getAllData();
    }
}
