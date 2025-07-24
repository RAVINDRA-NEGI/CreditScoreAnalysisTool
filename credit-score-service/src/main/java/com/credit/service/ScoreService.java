package com.credit.service;

import com.credit.dto.ScoreResponse;

public interface ScoreService {
	ScoreResponse calculateScore(Long userId);
}
