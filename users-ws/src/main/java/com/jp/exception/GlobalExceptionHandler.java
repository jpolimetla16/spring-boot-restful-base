package com.jp.exception;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import jakarta.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public void handleResourceNotFoundException(HttpServletResponse  response) throws IOException{
		response.sendError(HttpStatus.BAD_REQUEST.value());
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex) {
		Map<String,Object> map = new LinkedHashMap<>();
		List<String> errors = ex.getBindingResult().getAllErrors().stream().map(x->x.getDefaultMessage()).collect(Collectors.toList());
		map.put("timestamp", LocalDateTime.now());
		map.put("status", HttpStatus.BAD_REQUEST.value());
		map.put("validateErrors", errors);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
		
	}
	
	
	
	

}
