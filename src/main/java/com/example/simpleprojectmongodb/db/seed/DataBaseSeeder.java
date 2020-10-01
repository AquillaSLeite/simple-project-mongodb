package com.example.simpleprojectmongodb.db.seed;

import java.util.ArrayList;
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
	UserRepository userRepository;

	@Autowired
	UserService userService;

	List<User> users = new ArrayList<>();

	@Override
	public void run(String... args) throws Exception {
		this.userSeed();
		this.writeResources();
	}
	
	private void userSeed(){
		userRepository.deleteAll();
		users = Arrays.asList(
			userService.save(new User(null, "Aquilla Silva", "aquilla@aquilla.com", "123456")),
			userService.save(new User(null, "Alejandro Silva", "alejandro@alejandro.com", "123456")),
			userService.save(new User(null, "Maria Cicera", "maria@maria.com", "123456"))
		);
	}
	
	private void writeResources() {
		System.out.println("================== Starter: UserSeeder ==================");
		this.users.forEach(System.out::println);
		System.out.println("================== Finish: UserSeeder ==================");
	}

}
