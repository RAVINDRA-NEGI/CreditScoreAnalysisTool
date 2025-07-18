package com.user.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.user.entity.CreditRecord.CreditType;

import lombok.Data;

@Data
public class CreditRecordResponse {
    private Long id;
    private Long userId;
    private BigDecimal amount;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private boolean paid;
    private CreditType type;
    private LocalDateTime createdAt;
}

