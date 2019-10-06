package com.globallogic.microservices.controller.exception;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.globallogic.microservices.constant.ErrorCode;
import com.globallogic.microservices.constant.ErrorMessage;
import com.globallogic.microservices.model.Error;

@ControllerAdvice
public class AdminExceptionHandler {

	private AdminExceptionHandler() {
		super();
	}

	private static final Logger logger = LogManager.getLogger();

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Error> notFoundException() {
		Error error = new Error();
		error.setErrorCode(ErrorCode.ERR_13.getCode());
		error.setErrorMessage(ErrorMessage.ERR_13_MESSAGE.getMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<Error> authenticationException() {
		Error error = new Error();
		error.setErrorCode(ErrorCode.ERR_12.getCode());
		error.setErrorMessage(ErrorMessage.ERR_12_MESSAGE.getMessage());
		return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Error> internalServerException() {
		Error error = new Error();
		error.setErrorCode(ErrorCode.ERR_14.getCode());
		error.setErrorMessage(ErrorMessage.ERR_14_MESSAGE.getMessage());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
