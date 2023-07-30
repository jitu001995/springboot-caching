package com.ms.exception;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ms.dto.ApiResponseMessage;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
	private Logger logger=LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	//handle Resource not found Exception
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponseMessage> resourceNotFoundException(ResourceNotFoundException ex){
		logger.info("Exceptin Handler invoked");
		ApiResponseMessage response= ApiResponseMessage
				                      .builder()
				                      .message(ex.getMessage())
				                      .status(HttpStatus.NOT_FOUND)
				                      .success(true)
				                      .build();
		return new ResponseEntity(response,HttpStatus.NOT_FOUND);
		
	}
	
	// methodArgumentNotValidException
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
		List<ObjectError>allErrors= ex.getBindingResult().getAllErrors();
		Map<String,Object> response=new HashMap<>();
		allErrors.stream().forEach(objError->{
			String message = objError.getDefaultMessage();
			String field = ((FieldError)objError).getField();
			response.put(field, message);
		});
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		
	}
}
