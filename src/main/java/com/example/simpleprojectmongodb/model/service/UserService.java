package com.example.simpleprojectmongodb.model.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.simpleprojectmongodb.model.User;
import com.example.simpleprojectmongodb.model.generic.GenericService;

@Service
public class UserService extends GenericService<User> {

	BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

	public String generateBcrypt(String password) {
		String bcryptPassword = bcrypt.encode(password);
		return bcryptPassword;
	}
}
