package com.nhnacademy.exam.hotel.security;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class JwtExceptionHandler {

	@ExceptionHandler(HttpClientErrorException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public String handleHttpClientErrorException(HttpClientErrorException e) {
		return e.getMessage();
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String handleException(Exception e) {
		return e.getMessage();
	}
}
