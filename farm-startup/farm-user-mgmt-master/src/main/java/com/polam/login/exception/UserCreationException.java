package com.polam.login.exception;

public class UserCreationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public UserCreationException(String message, Throwable e) {
		super(message, e);
	}
	
}
