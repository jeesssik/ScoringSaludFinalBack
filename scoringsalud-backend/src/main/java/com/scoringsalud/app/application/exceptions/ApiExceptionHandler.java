package com.scoringsalud.app.application.exceptions;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ApiRequestException.class)
	public ResponseEntity<Object> handleApiRequestException(ApiRequestException e) {
		HttpStatus badRequest = HttpStatus.BAD_REQUEST;
		ApiException apiException = new ApiException(e.getMessage(), e, badRequest, LocalDateTime.now());
		return new ResponseEntity<>(apiException, badRequest);
	}

	@ExceptionHandler(ApiServerException.class)
	public ResponseEntity<Object> handleApiServerException(ApiServerException e) {
		HttpStatus serverError = HttpStatus.INTERNAL_SERVER_ERROR;
		ApiException apiException = new ApiException(e.getMessage(), e, serverError, LocalDateTime.now());
		return new ResponseEntity<>(apiException, serverError);
	}

	@ExceptionHandler(ApiNotFoundException.class)
	public ResponseEntity<Object> handleApiNotFoundException(ApiNotFoundException e, WebRequest request) {
		HttpStatus notFoundError = HttpStatus.NOT_FOUND;
		ApiException apiException = new ApiException(e.getMessage(), e, notFoundError,
				LocalDateTime.now());
		return new ResponseEntity<>(apiException, notFoundError);
	}

	@ExceptionHandler(ApiProcessingException.class)
	public ResponseEntity<Object> handleApiProcessingException(ApiProcessingException e) {
		HttpStatus serverError = HttpStatus.INTERNAL_SERVER_ERROR;
		ApiException apiException = new ApiException(e.getMessage(), e, serverError, LocalDateTime.now());
		return new ResponseEntity<>(apiException, serverError);
	}
}
