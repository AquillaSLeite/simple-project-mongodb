package com.example.simpleprojectmongodb.model.controller;

import java.net.URI;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class UtilController {
	
	static URI generatedUri(String id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
	}
}
