package com.example.simpleprojectmongodb.model.dto.request;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.context.annotation.Configuration;

import com.example.simpleprojectmongodb.model.User;

import lombok.Data;

@Configuration
@Data
public class UserUpdateDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotBlank @Size(min = 5, max = 50)
	private String name;
	
	@NotBlank @Email @Size(min = 5, max = 50)
	private String email;
	
	public User toResource() {
		return new User(null, this.name, this.email, null);
	}
}
