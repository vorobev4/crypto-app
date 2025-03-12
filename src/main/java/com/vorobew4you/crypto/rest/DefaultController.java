package com.vorobew4you.crypto.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/")
@RestController
public class DefaultController {

    @PostMapping("/test")
    public ResponseEntity<Void> getAllData() {

        return ResponseEntity.status(200).build();
    }
}
