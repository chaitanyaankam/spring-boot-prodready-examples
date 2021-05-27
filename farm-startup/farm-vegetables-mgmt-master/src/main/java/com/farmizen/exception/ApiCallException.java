package com.farmizen.exception;

public class ApiCallException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ApiCallException(String message) {
		super(message);
	}

}