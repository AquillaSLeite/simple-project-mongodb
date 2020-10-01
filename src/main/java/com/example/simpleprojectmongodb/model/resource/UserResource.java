package com.example.simpleprojectmongodb.model.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.simpleprojectmongodb.model.User;
import com.example.simpleprojectmongodb.model.dto.UserDTO;
import com.example.simpleprojectmongodb.model.resource.exception.ResourceNotFound;
import com.example.simpleprojectmongodb.model.service.UserService;

@RestController
@RequestMapping("api/users")
public class UserResource {
	
	@Autowired
	UserService service;
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> index(){
		return ResponseEntity.ok().body(service.findAll().stream().map(x -> new UserDTO(x)).collect(Collectors.toList()));
	}
	
	@PostMapping
	public ResponseEntity<UserDTO> store(@Valid @RequestBody User user){
		UserDTO userDTO = new UserDTO(service.save(user));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(userDTO);
	}
	
	@GetMapping(value = "/{id}")
	public UserDTO show(@PathVariable String id){
		Optional<User> user = service.findById(id);
		user.orElseThrow(() -> new ResourceNotFound("não encontrado"));
		return new UserDTO(new User(user.get().getId(), user.get().getName(), user.get().getEmail(), null));
	}
}
