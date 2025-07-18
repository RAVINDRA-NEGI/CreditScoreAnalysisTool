package com.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.dto.CreditRecordRequest;
import com.user.dto.CreditRecordResponse;
import com.user.service.CreditRecordService;

@RestController
@RequestMapping("/api/credit")
public class CreditController {

    @Autowired
    private CreditRecordService creditRecordService;

    @PostMapping("/add")
    public ResponseEntity<CreditRecordResponse> addCredit(
            @Validated @RequestBody CreditRecordRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(creditRecordService.addCreditRecord(request));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CreditRecordResponse>> getCreditsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(creditRecordService.getRecordsByUser(userId));
    }

    @PutMapping("/{id}/mark-paid")
    public ResponseEntity<CreditRecordResponse> markAsPaid(@PathVariable Long id) {
        return ResponseEntity.ok(creditRecordService.markAsPaid(id));
    }

}
