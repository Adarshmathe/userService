package com.user.userService.globalExceptionHandler;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import com.user.userService.util.customException;

@ControllerAdvice
public class ExceptionHandler {
	
	@org.springframework.web.bind.annotation.ExceptionHandler(customException.class)
	public ResponseEntity<Map<String,String>> customerException(customException ex) {
		
		return new ResponseEntity<Map<String,String>>( Map.of("code",ex.getEcode(),"message",ex.getMessage()),HttpStatus.OK);
	}

}
