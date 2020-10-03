package com.example.simpleprojectmongodb.model.controller.exception;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class StandardError implements Serializable {
	private static final long serialVersionUID = 1L;

	@Getter @Setter
	private Instant timestamp;
	
	@Getter @Setter
	private Integer status;
	
	@Getter
	private Map<String, String> errors = new HashMap<>();
	
	@Getter @Setter
	private String path;

	public StandardError(Instant timestamp, Integer status, String path) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.path = path;
	}
}
