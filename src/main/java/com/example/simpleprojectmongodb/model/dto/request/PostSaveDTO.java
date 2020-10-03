package com.example.simpleprojectmongodb.model.dto.request;

import java.time.Instant;

import com.example.simpleprojectmongodb.model.Post;
import com.example.simpleprojectmongodb.model.User;

public class PostSaveDTO {

	private Instant date;
	private String title;
	private String body;
	private User user;
	
	public Post toResource() {
		return new Post(null, this.date, this.title, this.body, this.user);
	}
}
