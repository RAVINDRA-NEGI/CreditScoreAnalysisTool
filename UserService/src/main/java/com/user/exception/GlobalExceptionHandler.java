package com.user.exception;


import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.user.dto.ErrorResponseDTO;



@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
			
	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<ErrorResponseDTO> handleUserAlreadyExistsException(UserAlreadyExistsException exception,
													WebRequest webrequest){
		ErrorResponseDTO errorResponseDto  = new ErrorResponseDTO(
				webrequest.getDescription(false),
				HttpStatus.CONFLICT , 
				exception.getMessage(),
				LocalDateTime.now()
				);
		
		return  new ResponseEntity<>(errorResponseDto, HttpStatus.CONFLICT);
	}
	
	 @ExceptionHandler(AdminAlreadyExistsException.class)
	public ResponseEntity<ErrorResponseDTO> handleAdminAlreadyExistsException(AdminAlreadyExistsException exception,
					WebRequest webrequest){
		ErrorResponseDTO errorResponseDto  = new ErrorResponseDTO(
			webrequest.getDescription(false),
			HttpStatus.CONFLICT , 
			exception.getMessage(),
			LocalDateTime.now()
			);
			
			return  new ResponseEntity<>(errorResponseDto, HttpStatus.CONFLICT);
			}
	
}
