package com.example.simpleprojectmongodb.model.resource;

import java.net.URI;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class UtilResource {
	
	static URI generatedUri(String id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
	}
}
