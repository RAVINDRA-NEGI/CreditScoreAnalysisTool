package com.user.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.user.dto.CreditRecordRequest;
import com.user.dto.CreditRecordResponse;
import com.user.entity.CreditRecord;
import com.user.exception.ResourceNotFoundExceptionException;
import com.user.repository.CreditRecordRepository;
import com.user.service.CreditRecordService;

public class CreditRecordServiceImpl  implements CreditRecordService{
	   @Autowired
	    private CreditRecordRepository repository;

	    @Override
	    public CreditRecordResponse addCreditRecord(CreditRecordRequest request) {
	        CreditRecord record = new CreditRecord();
	        record.setUserId(request.getUserId());
	        record.setAmount(request.getAmount());
	        record.setIssueDate(request.getIssueDate());
	        record.setDueDate(request.getDueDate());
	        record.setType(request.getType());
	        repository.save(record);
	        return toResponse(record);
	    }

	    @Override
	    public List<CreditRecordResponse> getRecordsByUser(Long userId) {
	        return repository.findbyUserId(userId)
	                .stream()
	                .map(this::toResponse)
	                .collect(Collectors.toList());
	    }

	    @Override
	    public CreditRecordResponse markAsPaid(Long id) {
	        CreditRecord record = repository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundExceptionException("Credit record not found with id: " + id));
	        if (record.isPaid()) {
	            throw new IllegalStateException("Credit record is already marked as paid.");
	        }
	        record.setPaid(true);
	        repository.save(record);
	        return toResponse(record);
	    }

	    private CreditRecordResponse toResponse(CreditRecord record) {
	        CreditRecordResponse response = new CreditRecordResponse();
	        BeanUtils.copyProperties(record, response);
	        return response;
	    }
	}
