package com.example.simpleprojectmongodb.model.dto.response;

import java.time.Instant;

import com.example.simpleprojectmongodb.model.Post;

import lombok.Data;

@Data
public class PostDTO {

	private String id;
	private Instant date;
	private String title;
	private String body;
	private AuthorDTO user;
	
	public static PostDTO fromResource(Post obj) {
		PostDTO dto = new PostDTO();
		dto.setId(obj.getId());
		dto.setDate(obj.getDate());
		dto.setTitle(obj.getTitle());
		dto.setBody(obj.getBody());
		dto.setUser(AuthorDTO.fromResource(obj.getUser()));
		return dto;
	}
	
}
