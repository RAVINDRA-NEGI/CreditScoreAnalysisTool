package com.credit.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.credit.dto.ScoreResponse;
import com.credit.service.ScoreService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/score")
public class ScoreController {
	
	private static final Logger logger = LoggerFactory.getLogger(ScoreController.class);
	
	private final ScoreService scoreService;
	
	 @GetMapping("/{userId}")
	    public ResponseEntity<ScoreResponse> getScore(@PathVariable Long userId) {
	        logger.info("Received request for credit score of userId: {}", userId);
	        ScoreResponse response = scoreService.calculateScore(userId);
	        logger.info("Returning score response for userId: {}", userId);
	        return ResponseEntity.ok(response);
	    }
	}

