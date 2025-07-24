package com.credit.service.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.credit.dto.CreditRecordDTO;

@FeignClient("credit-record-service")
public interface CreditRecordServiceClient {
	 @GetMapping("/credit-records/user/{userId}")
	    List<CreditRecordDTO> getCreditsByUser(@PathVariable Long userId);
}
