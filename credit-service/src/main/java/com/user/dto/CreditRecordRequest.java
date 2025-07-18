package com.user.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.user.entity.CreditRecord.CreditType;

import lombok.Data;

@Data
public class CreditRecordRequest {
    private Long userId;
    private BigDecimal amount;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private CreditType type;
}

