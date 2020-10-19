package com.scoringsalud.app.exceptions.application;

public class ApiServerException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ApiServerException(String message) {
		super(message);
	}

	public ApiServerException(String message, Throwable cause) {
		super(message, cause);
	}
}
