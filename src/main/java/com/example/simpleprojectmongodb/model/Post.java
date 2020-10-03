package com.example.simpleprojectmongodb.model;

import java.io.Serializable;
import java.time.Instant;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.simpleprojectmongodb.model.dto.response.AuthorDTO;
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
	
	@NotBlank @Size(min = 5, max = 50)
	private String title;
	
	@NotBlank @Size(min = 5, max = 150)
	private String body;

	@NotBlank
	private AuthorDTO user;
}
