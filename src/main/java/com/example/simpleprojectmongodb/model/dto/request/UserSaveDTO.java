package com.example.simpleprojectmongodb.model.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.example.simpleprojectmongodb.model.User;

import lombok.Data;

@Data
public class UserSaveDTO {
	private String id;

	@NotBlank @Size(min = 5, max = 50)
	private String name;
	
	@NotBlank @Email @Size(min = 5, max = 50)
	private String email;
	
	@NotBlank @NotEmpty
	private String password;
	
	public User toResource() {
		return new User(this.id, this.name, this.email, this.password);
	}
}
