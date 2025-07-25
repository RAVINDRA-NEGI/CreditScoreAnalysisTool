package com.credit.service.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.credit.dto.CreditRecordDTO;

@FeignClient(name = "credit-record-service" , fallback = CreditRecordFallback.class)
public interface CreditRecordServiceFeignClient {
	 @GetMapping("/credit-records/user/{userId}")
	    List<CreditRecordDTO> getCreditsByUser(@PathVariable Long userId);
}
