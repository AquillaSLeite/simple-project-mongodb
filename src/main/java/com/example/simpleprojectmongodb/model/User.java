package com.example.simpleprojectmongodb.model;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Document(collection = "users")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = "password")
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id @Getter
	private String id;
	
	@Getter @Setter @NotBlank @Size(min = 5, max = 50)
	private String name;
	
	@Getter @Setter @NotBlank @Email @Size(min = 5, max = 50)
	private String email;
	
	@Getter @Setter @NotBlank @NotEmpty
	private String password;
}
