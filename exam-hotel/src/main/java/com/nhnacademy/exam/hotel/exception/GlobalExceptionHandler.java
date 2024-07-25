package com.nhnacademy.exam.hotel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<Map<String, String>> handleBadRequestException(BadRequestException ex) {
		Map<String, String> response = new HashMap<>();
		response.put("errorMessage", "잘못된 요청입니다.");
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(SystemException.class)
	public ResponseEntity<Map<String, String>> handleSystemException(SystemException ex) {
		Map<String, String> response = new HashMap<>();
		response.put("errorMessage", "시스템에서 에러가 발생했습니다.");
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, String>> handleException(Exception ex) {
		Map<String, String> response = new HashMap<>();
		response.put("errorMessage", "시스템에서 에러가 발생했습니다.");
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
