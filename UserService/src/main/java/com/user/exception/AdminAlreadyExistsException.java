package com.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class AdminAlreadyExistsException  extends RuntimeException{
	public AdminAlreadyExistsException(String message) {
		super(message);
	}
}
