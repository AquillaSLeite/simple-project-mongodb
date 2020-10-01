package com.example.simpleprojectmongodb.model.dto;

import java.io.Serializable;

import com.example.simpleprojectmongodb.model.User;

import lombok.Getter;
import lombok.Setter;

public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Getter @Setter
	private String id;
	
	@Getter @Setter
	private String name;
	
	@Getter @Setter 
	private String email;
	
	public UserDTO() {
		
	}
	
	public UserDTO(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
	}
}
