package com.user.service;

import java.util.List;

import com.user.dto.CreditRecordRequest;
import com.user.dto.CreditRecordResponse;

public interface CreditRecordService {
	  CreditRecordResponse addCreditRecord(CreditRecordRequest request);
	  List<CreditRecordResponse> getRecordsByUser(Long userId);
	  CreditRecordResponse markAsPaid(Long id);
}
