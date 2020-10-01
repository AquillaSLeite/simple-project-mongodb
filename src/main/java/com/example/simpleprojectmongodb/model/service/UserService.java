package com.example.simpleprojectmongodb.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.simpleprojectmongodb.model.User;
import com.example.simpleprojectmongodb.model.repository.UserRepository;
import com.example.simpleprojectmongodb.model.service.exception.AlreadyEmailException;

@Service
public class UserService {
	
	@Autowired
	UserRepository repository;
	
	BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
	
	public List<User> findAll(){
		return repository.findAll();
	}
	
	public Optional<User> findById(String id) {
		Optional<User> user = repository.findById(id);
		return user;
	}
	
	public User save(User user){
		if(emailExists(user.getEmail())) {
			throw new AlreadyEmailException("e-mail já existente na base de dados");
		}
		user.setPassword(generateBcrypt(user.getPassword()));
		return repository.save(user);
	}
	
	private User findByEmail(String email) {
		return repository.findByEmail(email);
	}
	
	private boolean emailExists(String email) {
		return findByEmail(email) == null ? false : true;
	}
	
	private String generateBcrypt(String password) {
		String bcryptPassword = bcrypt.encode(password);
		return bcryptPassword;
	}
	
	@SuppressWarnings("unused")
	private boolean compareRawPasswordEncodedPassword(String rawPassword, String encodedPassword) {
		return bcrypt.matches(rawPassword, encodedPassword);
	}
	
}
