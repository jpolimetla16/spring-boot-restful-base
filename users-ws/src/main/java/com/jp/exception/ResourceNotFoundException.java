package com.jp.exception;

public class ResourceNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 3686244145822896983L;

	public ResourceNotFoundException(String message) {
		super(message);
	}
}
