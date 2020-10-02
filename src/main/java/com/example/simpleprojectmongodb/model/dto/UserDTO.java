package com.example.simpleprojectmongodb.model.dto;

import java.io.Serializable;

import org.springframework.context.annotation.Configuration;

import com.example.simpleprojectmongodb.model.User;
import com.example.simpleprojectmongodb.model.generic.GenericDTO;

import lombok.Getter;
import lombok.Setter;

@Configuration
public class UserDTO implements GenericDTO<User>, Serializable {
	private static final long serialVersionUID = 1L;
	
	@Getter @Setter
	private String id;
	
	@Getter @Setter
	private String name;
	
	@Getter @Setter 
	private String email;
	
	public UserDTO() {
		
	}
	
	public UserDTO(String id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}
	
	
	public UserDTO generateDto(User user) {
		return new UserDTO(user.getId(), user.getName(), user.getEmail());
	}
}
