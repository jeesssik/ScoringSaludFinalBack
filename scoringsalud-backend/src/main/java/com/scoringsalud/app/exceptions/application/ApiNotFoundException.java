package com.scoringsalud.app.exceptions.application;

public class ApiNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ApiNotFoundException(String message) {
		super(message);
	}

	public ApiNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}