package com.user.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.constants.Constants;
import com.user.dto.CreditRecordRequest;
import com.user.dto.CreditRecordResponse;
import com.user.dto.ResponseDTO;
import com.user.service.CreditRecordService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/credit-records")
public class CreditController {
	
	private static final Logger logger = LoggerFactory.getLogger(CreditController.class);
 
    private  final CreditRecordService creditRecordService;
    
    

    @PostMapping("/add")
    public ResponseEntity<ResponseDTO> addCredit(
            @Validated @RequestBody CreditRecordRequest request) {
    	creditRecordService.addCreditRecord(request);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body ( new ResponseDTO(Constants.STATUS_200,Constants.MESSAGE_200));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CreditRecordResponse>> getCreditsByUser(@PathVariable Long userId) {
    	List<CreditRecordResponse> crr = creditRecordService.getRecordsByUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(crr);
    }

    @PutMapping("/{id}/mark-paid")
    public ResponseEntity<ResponseDTO> markAsPaid(@PathVariable Long id) {
    	creditRecordService.markAsPaid(id);
         return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body ( new ResponseDTO(Constants.STATUS_200,Constants.MESSAGE_200));
        		
    }

}
