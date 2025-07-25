package com.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    @GetMapping("/fallback/credit")
    public ResponseEntity<String> creditRecordServiceFallback() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Credit Record Service is currently unavailable. Please try again later.");
    }

    @GetMapping("/fallback/score")
    public ResponseEntity<String> creditScoreServiceFallback() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Credit Score Service is currently unavailable. Please try again later.");
        
    }
    @GetMapping("/fallback/user")
    public ResponseEntity<String> userServiceFallback() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("User Service is currently unavailable. Please try again later.");
        
    }
}
