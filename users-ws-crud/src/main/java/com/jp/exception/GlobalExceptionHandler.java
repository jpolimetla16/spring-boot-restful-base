package com.jp.exception;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleNotFoundException(ResourceNotFoundException exe,HttpServletRequest request) throws IOException {
		ApiError apiError = new ApiError(LocalDateTime.now(), 
					HttpStatus.NOT_FOUND.value(), 
					HttpStatus.NOT_FOUND.getReasonPhrase(), 
					exe.getMessage(),request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}
	
}
