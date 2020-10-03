package com.example.simpleprojectmongodb.model.dto.request;

import java.time.Instant;

import com.example.simpleprojectmongodb.model.Post;

import lombok.Data;

@Data
public class PostUpdateDTO {
	private String id;
	private Instant date;
	private String title;
	private String body;
	
	public Post toResource() {
		return new Post(this.id, this.date, this.title, this.body, null);
	}
}
