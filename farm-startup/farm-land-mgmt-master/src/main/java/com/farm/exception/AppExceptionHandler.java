package com.farm.exception;

import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.data.rest.core.RepositoryConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class AppExceptionHandler {
	
	@ExceptionHandler({ RepositoryConstraintViolationException.class })
	public ResponseEntity<Object> handleAccessDeniedException(Exception ex, WebRequest request) {
		RepositoryConstraintViolationException nevEx = (RepositoryConstraintViolationException) ex; 
		String errors = nevEx.getErrors().getAllErrors().stream()
				.map(p -> p.toString()).collect(Collectors.joining("\n"));           
		return new ResponseEntity<Object>(errors, new HttpHeaders(), HttpStatus.PARTIAL_CONTENT);
	}

	@ExceptionHandler({ TransactionSystemException.class })
	public ResponseEntity<?> handleAccessDeniedException2(Exception ex, WebRequest request) {
		Throwable cause = ((TransactionSystemException) ex).getRootCause();
		if (cause instanceof ConstraintViolationException) {
			Set<ConstraintViolation<?>> constraintViolations = ((ConstraintViolationException) cause).getConstraintViolations();
			String errors = constraintViolations.stream().map(e -> e.getPropertyPath()+" "+e.getMessage()).collect(Collectors.joining("\n"));   
			return new ResponseEntity<Object>(errors, new HttpHeaders(), HttpStatus.PARTIAL_CONTENT);
		} else
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	@ExceptionHandler(value = { ResourceNotFoundException.class, FarmBookingException.class })
	protected ResponseEntity<?> badRequest(RuntimeException ex, WebRequest request) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}

	@ExceptionHandler(value = { JwtAuthenticationException.class })
	protected ResponseEntity<?> unAuthorizedRequest(RuntimeException ex, WebRequest request) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
	}
	
	@ExceptionHandler(value = { ApiCallException.class })
	protected ResponseEntity<?> internalServer(RuntimeException ex, WebRequest request) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
	}
}
