package com.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundExceptionException  extends RuntimeException{
	public ResourceNotFoundExceptionException(String message) {
		super(message);
	}
}
