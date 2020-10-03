package com.example.simpleprojectmongodb.model.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.simpleprojectmongodb.model.User;
import com.example.simpleprojectmongodb.model.dto.request.UserSaveDTO;
import com.example.simpleprojectmongodb.model.dto.request.UserUpdateDTO;
import com.example.simpleprojectmongodb.model.dto.response.UserDTO;
import com.example.simpleprojectmongodb.model.repository.UserRepository;
import com.example.simpleprojectmongodb.model.resource.exception.ResourceNotFound;
import com.example.simpleprojectmongodb.model.service.exception.AlreadyEmailException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;

	private BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
	
	// Private methods
	private User findAndValidateById(String id) {
		Optional<User> obj = repository.findById(id);
		obj.orElseThrow(() -> new ResourceNotFound("não encontrado"));
		return obj.get();
	}
	
	private User findOneByEmail(String email) {
		return repository.findByEmail(email);
	}
	
	private boolean emailExists(String email) {
		return findOneByEmail(email) == null ? false : true;
	}
	
	private String generateBcrypt(String password) {
		String bcryptPassword = bcrypt.encode(password);
		return bcryptPassword;
	}
	
	// Public methods	
	public List<UserDTO> findAll(int page, int size){
		return repository.findAll(UtilService.createPageRequest(page, size, Sort.Direction.ASC, "name"))
				.stream()
				.map(obj -> UserDTO.fromResource(obj))
				.collect(Collectors.toList());
	}
	
	public UserDTO findById(String id) {
		return UserDTO.fromResource(this.findAndValidateById(id));
	}

	public List<UserDTO> findByNameOrEmail(String filter, int page, int size){
		return repository.findByNameOrEmail(filter, UtilService.createPageRequest(page, size, Sort.Direction.ASC, "name"))
				.stream()
				.map(obj -> UserDTO.fromResource(obj))
				.collect(Collectors.toList());
	}

	public UserDTO save(UserSaveDTO body) {
		if(emailExists(body.getEmail())) {
			throw new AlreadyEmailException("e-mail já existente na base de dados");
		}
		User obj = body.toResource();
		obj.setPassword(this.generateBcrypt(obj.getPassword()));
		return UserDTO.fromResource(repository.save(obj));
	}
	
	public UserDTO update(String id, UserUpdateDTO body) {
		User obj = this.findAndValidateById(id);
		User dto = body.toResource();
		BeanUtils.copyProperties(dto, obj, "id");
		return UserDTO.fromResource(repository.save(obj));
	}
	
	public void delete(String id) {
		repository.deleteById(this.findAndValidateById(id).getId());
	}
}
