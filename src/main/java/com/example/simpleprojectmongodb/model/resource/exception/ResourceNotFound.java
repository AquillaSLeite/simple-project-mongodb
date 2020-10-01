package com.example.simpleprojectmongodb.model.resource.exception;

public class ResourceNotFound extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ResourceNotFound(String messageError) {
		super(messageError);
	}

}
