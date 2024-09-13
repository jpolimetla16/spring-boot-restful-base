package com.jp.exception;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value = ResourceNotFoundException.class)
	public void handleResourceNotFoundException(HttpServletResponse  response) throws IOException{
		response.sendError(HttpStatus.BAD_REQUEST.value());
	}
	
	

}
