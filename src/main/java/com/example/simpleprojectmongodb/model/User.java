package com.example.simpleprojectmongodb.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Document(collection = "users")
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
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
	
	@Getter @DBRef(lazy = true)
	List<Post> posts = new ArrayList<>();

	public User(String id, String name, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}
}
