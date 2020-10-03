package com.example.simpleprojectmongodb.model.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.simpleprojectmongodb.model.User;
import com.example.simpleprojectmongodb.model.dto.UserDTO;
import com.example.simpleprojectmongodb.model.service.UserService;

@RestController
@RequestMapping("api/users")
@Validated
public class UserResource {

	@Autowired
	private UserService service;

	ModelMapper modelMapper = new ModelMapper();

	private UserDTO transformDto(User user) {
		return modelMapper.map(user, UserDTO.class);
	}

	@GetMapping
	public ResponseEntity<List<UserDTO>> index(
			@RequestParam(value = "page", defaultValue = "0") @Min(0) Integer page,
			@RequestParam(value = "size", defaultValue = "10") @Min(1) Integer size
	) {
		List<UserDTO> userDto = service
				.findAll(page, size)
				.stream()
				.map(user -> this.transformDto(user))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(userDto);
	}

	@GetMapping("/search")
	public ResponseEntity<List<UserDTO>> indexBySearch(
			@RequestParam(value = "filter", defaultValue = "") @NotBlank String filter,
			@RequestParam(value = "page", defaultValue = "0") @Min(0) Integer page,
			@RequestParam(value = "size", defaultValue = "10") @Min(1) Integer size
	) {
		List<UserDTO> userDto = service
				.findByNameOrEmail(filter, page, size)
				.stream()
				.map(user -> this.transformDto(user))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(userDto);
	}

	@PostMapping
	public ResponseEntity<UserDTO> store(@Valid @RequestBody User user) {
		user = service.save(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(this.transformDto(user));
	}

	@GetMapping(value = "/{id}")
	public UserDTO show(@PathVariable String id) {
		return this.transformDto(service.findById(id));
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<UserDTO> update(@PathVariable String id, @Valid @RequestBody User entity) {
		User objUser = service.findById(id);
		BeanUtils.copyProperties(entity, objUser, "id");
		return ResponseEntity.ok(this.transformDto(service.save(objUser)));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> update(@PathVariable String id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
