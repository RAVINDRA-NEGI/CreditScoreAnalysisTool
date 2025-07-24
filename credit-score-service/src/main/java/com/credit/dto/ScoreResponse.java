package com.credit.dto;

import lombok.Data;

@Data
public class ScoreResponse {
	private Long userId;
	private int score;
	private String rating;
}
