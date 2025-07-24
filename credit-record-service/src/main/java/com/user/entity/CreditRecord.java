package com.user.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "credit_records")
@Getter@Setter@NoArgsConstructor@AllArgsConstructor@ToString
public class CreditRecord {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    private Long userId;

    
    private BigDecimal amount;

    
    private LocalDate issueDate;

   
    private LocalDate dueDate;

    private boolean paid = false;

    @Enumerated(EnumType.STRING)
    
    private CreditType type;

    private LocalDateTime createdAt = LocalDateTime.now();

    public enum CreditType {
        LOAN,
        CREDIT_CARD,
        MORTGAGE,
        BNPL
    }

}