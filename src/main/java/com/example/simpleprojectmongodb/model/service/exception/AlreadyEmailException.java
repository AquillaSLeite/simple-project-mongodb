package com.example.simpleprojectmongodb.model.service.exception;

public class AlreadyEmailException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public AlreadyEmailException(String messageError) {
		super(messageError);
	}

}
