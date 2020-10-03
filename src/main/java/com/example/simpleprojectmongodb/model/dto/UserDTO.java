package com.example.simpleprojectmongodb.model.dto;

import java.io.Serializable;

import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private String email;
}
