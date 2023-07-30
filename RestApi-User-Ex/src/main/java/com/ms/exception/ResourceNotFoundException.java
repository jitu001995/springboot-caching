package com.ms.exception;

import lombok.Builder;

@Builder
public class ResourceNotFoundException extends RuntimeException {
   
	public ResourceNotFoundException() {
		super("Resource Not Found !!");
	}
	
	public ResourceNotFoundException(String msg) {
		super(msg);
	}
	
}
