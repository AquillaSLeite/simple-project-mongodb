package com.example.simpleprojectmongodb.db.seed;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.example.simpleprojectmongodb.model.Post;
import com.example.simpleprojectmongodb.model.User;
import com.example.simpleprojectmongodb.model.dto.response.AuthorDTO;
import com.example.simpleprojectmongodb.model.repository.PostRepository;
import com.example.simpleprojectmongodb.model.repository.UserRepository;
import com.example.simpleprojectmongodb.model.service.UserService;

@Configuration
public class DataBaseSeeder implements CommandLineRunner{

	ModelMapper modelMapper = new ModelMapper();
	
	@Autowired 
	UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		userRepository.deleteAll();
		postRepository.deleteAll();

		User user1 = new User(null, "Aquilla Silva", "aquilla@aquilla.com", "123456");
		User user2 = new User(null, "Alejandro Silva", "alejandro@alejandro.com","123456");
		User user3 = new User(null, "Maria Cicera", "maria@maria.com", "123456");

		List<User> users = userRepository.saveAll(Arrays.asList(user1, user2, user3));
		
		for(User user : users) {
			Post post1 = new Post(null, Instant.now(), user.getName() + " - title1", "body1", user);
			Post post2 = new Post(null, Instant.now(), user.getName() + " - title2", "body2", user);
			Post post3 = new Post(null, Instant.now(), user.getName() + " - title3", "body3", user);
			postRepository.saveAll(Arrays.asList(post1, post2, post3));
			
			user.getPosts().addAll(Arrays.asList(post1, post2, post3));
			
			userRepository.save(user);
		}
	}
}
