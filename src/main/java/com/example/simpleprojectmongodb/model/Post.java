package com.example.simpleprojectmongodb.model;

import java.io.Serializable;
import java.time.Instant;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "posts")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Post implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	
	@Getter @Setter @NotBlank @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT")
	private Instant date;
	
	@Getter @Setter @NotBlank @Size(min = 5, max = 50)
	private String title;
	
	@Getter @Setter @NotBlank @Size(min = 5, max = 150)
	private String body;

	@Getter @NotBlank
	private String user;

}
