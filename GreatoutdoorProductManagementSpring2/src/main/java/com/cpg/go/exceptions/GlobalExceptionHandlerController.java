package com.cpg.go.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandlerController extends ResponseEntityExceptionHandler{

	@ExceptionHandler(value = ProductException.class)
	public ResponseEntity<Object> handleProductException(ProductException exception)
	{
	   ExceptionResponse  exceptionResponse=new ExceptionResponse(new Date(),"Failed",exception.getErrorsMap());
		return new ResponseEntity<>(exceptionResponse,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value=LoginException.class)
	public ResponseEntity<Object> handleLoginException(LoginException exception)
	{
		return new ResponseEntity<>(exception.getErrorsMap(),HttpStatus.BAD_REQUEST);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return new ResponseEntity<>("You cannot fetch data in this format ",HttpStatus.BAD_REQUEST);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		return new ResponseEntity<>("The content type your uploading is not supported",HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		return new ResponseEntity<>("This Request method is not supported",HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}
}
