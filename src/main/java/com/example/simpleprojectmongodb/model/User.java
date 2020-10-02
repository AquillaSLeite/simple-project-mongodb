package com.example.simpleprojectmongodb.model;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.data.mongodb.core.mapping.Document;

import com.example.simpleprojectmongodb.model.generic.GenericModel;
import com.example.simpleprojectmongodb.model.service.UserService;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Document(collection = "users")
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "password", callSuper = true)
public class User extends GenericModel implements Serializable{
	private static final long serialVersionUID = 1L;

	@Getter @Setter @NotBlank @Size(min = 5, max = 50)
	private String name;
	
	@Getter @Setter @NotBlank @Email @Size(min = 5, max = 50)
	private String email;
	
	@Getter @NotBlank @NotEmpty
	private String password;

	public void setPassword(String password) {
		UserService userService = new UserService();
		this.password = userService.generateBcrypt(password);
	}		
}
