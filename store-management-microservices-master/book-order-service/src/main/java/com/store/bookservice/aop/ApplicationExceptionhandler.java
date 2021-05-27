package com.store.bookservice.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.store.bookservice.exception.ResourceNotFoundException;

@ControllerAdvice
public class ApplicationExceptionhandler extends ResponseEntityExceptionHandler {
	
	private static Logger log = LoggerFactory.getLogger(ApplicationExceptionhandler.class);
	
	@ExceptionHandler(value = {IllegalArgumentException.class, IllegalStateException.class})
	protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
		String bodyOfResponse = "Conflict Arised";
		logException(ex);
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
	}
	
	@ExceptionHandler(value = {ResourceNotFoundException.class})
	protected ResponseEntity<Object> handleNotFound(RuntimeException ex, WebRequest request) {
		String bodyOfResponse = "The resource you were trying to reach is not found";
		logException(ex);
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler(value = {Exception.class})
	protected ResponseEntity<Object> handleRest(RuntimeException ex, WebRequest request) {
		String bodyOfResponse = "Server Error";
		logException(ex);
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}
	
	private void logException(Exception ex) {
		log.error(ex.getLocalizedMessage(), ex);
	}
}
