package com.example.simpleprojectmongodb.model.dto.response;

import java.io.Serializable;

import com.example.simpleprojectmongodb.model.User;

import lombok.Data;

@Data
public class AuthorDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	
	public static AuthorDTO fromResource(User obj) {
		AuthorDTO dto = new AuthorDTO();
		dto.setId(obj.getId());
		dto.setName(obj.getName());
		return dto;
	}
}
