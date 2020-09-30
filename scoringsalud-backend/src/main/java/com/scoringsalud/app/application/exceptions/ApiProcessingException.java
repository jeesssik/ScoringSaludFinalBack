package com.scoringsalud.app.application.exceptions;

public class ApiProcessingException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ApiProcessingException(String message) {
		super(message);
	}

	public ApiProcessingException(String message, Throwable cause) {
		super(message, cause);
	}
}