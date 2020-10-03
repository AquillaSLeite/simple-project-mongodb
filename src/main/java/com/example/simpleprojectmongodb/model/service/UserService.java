package com.example.simpleprojectmongodb.model.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.simpleprojectmongodb.model.User;
import com.example.simpleprojectmongodb.model.repository.UserRepository;
import com.example.simpleprojectmongodb.model.resource.exception.ResourceNotFound;
import com.example.simpleprojectmongodb.model.service.exception.AlreadyEmailException;
import com.example.simpleprojectmongodb.model.service.util.Util;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	Util util = new Util();

//	private CustomUserRepository customUserRepositoryImpl;

	BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
	
	public Page<User> findAll(int page, int size){
		return repository.findAll(util.createPageRequest(page, size, Sort.Direction.ASC, "name"));
	}
	
	public User findById(String id) {
		Optional<User> recoverUser = repository.findById(id);
		recoverUser.orElseThrow(() -> new ResourceNotFound("não encontrado"));
		return recoverUser.get();
	}
	
	private User findOneByEmail(String email) {
		return repository.findByEmail(email);
	}
	
	public Page<User> findByNameOrEmail(String filter, int page, int size){
		return repository.findByNameOrEmail(filter, util.createPageRequest(page, size, Sort.Direction.ASC, "name"));
	}

	public User save(User user) {
		if(emailExists(user.getEmail())) {
			throw new AlreadyEmailException("e-mail já existente na base de dados");
		}
		return repository.save(user);
	}
	
	public void delete(String id) {
		repository.delete(this.findById(id));
	}

	public String generateBcrypt(String password) {
		String bcryptPassword = bcrypt.encode(password);
		return bcryptPassword;
	}
	
	private boolean emailExists(String email) {
		return findOneByEmail(email) == null ? false : true;
	}
}
