package com.credit.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.credit.dto.CreditRecordDTO;
import com.credit.dto.ScoreResponse;
import com.credit.service.ScoreService;
import com.credit.service.client.CreditRecordServiceClient;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ScoreServiceImpl implements ScoreService {
	
	  private static final Logger logger = LoggerFactory.getLogger(ScoreServiceImpl.class);

	   
	    private final CreditRecordServiceClient creditServiceClient;

	    @Override
	    public ScoreResponse calculateScore(Long userId) {
	        logger.info("Calculating score for userId: {}", userId);

	        List<CreditRecordDTO> records = creditServiceClient.getCreditsByUser(userId);
	        logger.debug("Fetched {} credit records for userId: {}", records.size(), userId);

	        int score = 700; // base score

	        long unpaidCount = records.stream().filter(r -> !r.isPaid()).count();
	        long paidCount = records.stream().filter(CreditRecordDTO::isPaid).count();
	        BigDecimal totalDebt = records.stream()
	                .filter(r -> !r.isPaid())
	                .map(CreditRecordDTO::getAmount)
	                .reduce(BigDecimal.ZERO, BigDecimal::add);

	        logger.debug("User {} unpaid count: {}, paid count: {}, total unpaid debt: {}", userId, unpaidCount, paidCount, totalDebt);

	        if (unpaidCount > 5) {
				score -= 100;
			}
	        if (totalDebt.compareTo(new BigDecimal("100000")) > 0) {
				score -= 50;
			}
	        if (paidCount > 5) {
				score += 50;
			}

	        String rating;
	        if (score >= 750) {
				rating = "Excellent";
			} else if (score >= 700) {
				rating = "Good";
			} else if (score >= 600) {
				rating = "Fair";
			} else {
				rating = "Poor";
			}

	        logger.info("Calculated score for user {}: {}, Rating: {}", userId, score, rating);

	        ScoreResponse response = new ScoreResponse();
	        response.setUserId(userId);
	        response.setScore(score);
	        response.setRating(rating);

	        return response;
	    }
}
