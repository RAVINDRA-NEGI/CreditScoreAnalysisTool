package com.user.exception;


import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.user.dto.ErrorResponseDTO;



@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	  @ExceptionHandler(ResourceNotFoundExceptionException.class)
	    public ResponseEntity<ErrorResponseDTO> handleNotFound(ResourceNotFoundExceptionException ex , WebRequest webrequest) {
		  	ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
		  			webrequest.getDescription(false), 
		  			HttpStatus.NOT_FOUND,
		  			ex.getMessage(), 
		  			LocalDateTime.now()
		  			);
		  
	        return new ResponseEntity<>(errorResponseDTO , HttpStatus.NOT_FOUND);
	    } 

	    @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<String> handleValidation(MethodArgumentNotValidException ex) {
	        String errorMessage = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
	        return ResponseEntity.badRequest().body(errorMessage);
	    }

	    @ExceptionHandler(IllegalStateException.class)
	    public ResponseEntity<String> handleIllegalState(IllegalStateException ex) {
	        return ResponseEntity.badRequest().body(ex.getMessage());
	    }

	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<String> handleGeneric(Exception ex) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
	    }
	    
	    
}
