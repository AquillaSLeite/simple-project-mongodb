package com.example.simpleprojectmongodb.db.seed;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.example.simpleprojectmongodb.model.User;
import com.example.simpleprojectmongodb.model.repository.UserRepository;
import com.example.simpleprojectmongodb.model.service.UserService;

@Configuration
public class DataBaseSeeder implements CommandLineRunner{

	@Autowired 
	UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {

		userRepository.deleteAll();
		User user1 = new User("Aquilla Silva", "aquilla@aquilla.com", userService.generateBcrypt("123456"));
		User user2 = new User("Alejandro Silva", "alejandro@alejandro.com", userService.generateBcrypt("123456"));
		User user3 = new User("Maria Cicera", "maria@maria.com", userService.generateBcrypt("123456"));

		List<User> users = userRepository.saveAll(Arrays.asList(user1, user2, user3));
		users.forEach(System.out::println);
	}
}
