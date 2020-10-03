package com.example.simpleprojectmongodb.model.dto.response;

import java.io.Serializable;

import com.example.simpleprojectmongodb.model.User;

import lombok.Data;

@Data
public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private String email;
	
	public static UserDTO fromResource(User obj) {
		UserDTO dto = new UserDTO();
		dto.setId(obj.getId());
		dto.setName(obj.getName());
		dto.setEmail(obj.getEmail());
		return dto;
	}
}
