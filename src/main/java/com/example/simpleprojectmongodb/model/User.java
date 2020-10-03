package com.example.simpleprojectmongodb.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
@Getter
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;

	@Setter
	private String name;
	
	@Setter
	private String email;
	
	@Setter
	private String password;
	
	@DBRef(lazy = true)
	List<Post> posts = new ArrayList<>();

	public User(String id, String name, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}
}
