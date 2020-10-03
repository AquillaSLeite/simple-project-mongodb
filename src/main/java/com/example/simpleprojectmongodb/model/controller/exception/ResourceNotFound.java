package com.example.simpleprojectmongodb.model.controller.exception;

public class ResourceNotFound extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ResourceNotFound(String messageError) {
		super(messageError);
	}

}
