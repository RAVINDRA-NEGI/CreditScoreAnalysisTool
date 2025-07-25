package com.credit.service.client;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.credit.dto.CreditRecordDTO;

@Component
public class CreditRecordFallback  implements CreditRecordServiceFeignClient {

	@Override
	public List<CreditRecordDTO> getCreditsByUser(Long userId) {
	
		return Collections.emptyList();
	}

}
