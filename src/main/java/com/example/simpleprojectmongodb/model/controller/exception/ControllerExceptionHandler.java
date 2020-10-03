package com.example.simpleprojectmongodb.model.controller.exception;

import java.time.Instant;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.example.simpleprojectmongodb.model.service.exception.AlreadyEmailException;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> MethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), request.getRequestURI());

		List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
		for (FieldError fieldError: fieldErrors) {
			err.getErrors().put(fieldError.getField(), fieldError.getDefaultMessage());
        }
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(AlreadyEmailException.class)
	public ResponseEntity<StandardError> AlreadyEmailException(AlreadyEmailException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), request.getRequestURI());
		
		err.getErrors().put("email", e.getMessage());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(ResourceNotFound.class)
	public ResponseEntity<StandardError> ResourceNotFound(ResourceNotFound e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), request.getRequestURI());
		
		err.getErrors().put("resource", e.getMessage());
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<StandardError> ConstraintViolationException(ConstraintViolationException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), request.getRequestURI());
		
		for(ConstraintViolation<?> fieldError : e.getConstraintViolations()) {
			int lastIndex = fieldError.getPropertyPath().toString().lastIndexOf(".") + 1;
			lastIndex = lastIndex < 0 ? 0 : lastIndex;			
			String propertyField = fieldError.getPropertyPath().toString().substring(lastIndex);
			err.getErrors().put(propertyField, fieldError.getMessage());
		}
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<StandardError> MethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), request.getRequestURI());

		err.getErrors().put(e.getName(), e.getMessage());
		return ResponseEntity.status(status).body(err);
	}
}
