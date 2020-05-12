package com.otp.exception;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class RestControllerHndler  extends ResponseEntityExceptionHandler{


	 @ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiError> handleResourceNotFound(final ResourceNotFoundException ex,
				final HttpServletRequest request) {

		ApiError errorDetails = new ApiError();

		errorDetails.setTimestamp(LocalDateTime.now());
		errorDetails.setErrorMessage(ex.getMessage());
		errorDetails.callerURL(request.getRequestURI());

		errorDetails.setStatus(HttpStatus.NOT_FOUND.value());

		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
		}
	 
	 
	 @ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	 public final ResponseEntity<ApiError> handleAllExceptions(Exception ex, WebRequest request) {
		ApiError errorDetails = new ApiError();

		errorDetails.setTimestamp(LocalDateTime.now());
		errorDetails.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

		errorDetails.setErrorMessage(ex.getMessage());
		errorDetails.callerURL(((HttpServletRequest) request).getRequestURI());

		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	 }





}

