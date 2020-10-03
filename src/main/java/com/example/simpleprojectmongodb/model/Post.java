package com.example.simpleprojectmongodb.model;

import java.io.Serializable;
import java.time.Instant;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Document(collection = "posts")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Data
public class Post implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	@NotBlank @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT")
	private Instant date;
	private String title;
	private String body;
	@DBRef(lazy = true)
	private User user;
}
