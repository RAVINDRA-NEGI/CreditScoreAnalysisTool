package com.credit.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class CreditRecordDTO {
	private Long id;
	private Long userId;
	private BigDecimal amount;
	private LocalDate issueDate;
	private LocalDate dueDate;
	private boolean paid;
	private CreditType type;
	
	  public enum CreditType {
	        LOAN,
	        CREDIT_CARD,
	        MORTGAGE,
	        BNPL
	    }
}
