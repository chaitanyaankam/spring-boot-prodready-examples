package com.farmizen.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class AppExceptionHandler {

	@ExceptionHandler(value = { ResourceNotFoundException.class })
	protected ResponseEntity<?> badRequest(RuntimeException ex, WebRequest request) {
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(ex.getMessage());
	}
	
	@ExceptionHandler(value = { JwtAuthenticationException.class })
	protected ResponseEntity<?> unAuthorizedRequest(RuntimeException ex, WebRequest request) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
	}

}
