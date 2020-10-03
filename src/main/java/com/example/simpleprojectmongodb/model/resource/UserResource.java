package com.example.simpleprojectmongodb.model.resource;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

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

import com.example.simpleprojectmongodb.model.User;
import com.example.simpleprojectmongodb.model.dto.request.UserSaveDTO;
import com.example.simpleprojectmongodb.model.dto.request.UserUpdateDTO;
import com.example.simpleprojectmongodb.model.dto.response.UserDTO;
import com.example.simpleprojectmongodb.model.service.UserService;

@RestController
@RequestMapping("api/users")
@Validated
public class UserResource {

	@Autowired
	private UserService service;

	@GetMapping
	public ResponseEntity<List<UserDTO>> index(
			@RequestParam(value = "page", defaultValue = "0") @Min(0) Integer page,
			@RequestParam(value = "size", defaultValue = "10") @Min(1) Integer size
	) {
		return ResponseEntity.ok().body(service.findAll(page, size));
	}

	@PostMapping
	public ResponseEntity<UserDTO> store(@Valid @RequestBody UserSaveDTO body) {
		UserDTO bodyDTO = service.save(body);
		return ResponseEntity.created(UtilResource.generatedUri(bodyDTO.getId())).body(bodyDTO);
	}

	@GetMapping(value = "/{id}")
	public UserDTO show(@PathVariable String id) {
		return service.findById(id);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<UserDTO> update(@PathVariable String id, @Valid @RequestBody UserUpdateDTO body) {
		return ResponseEntity.ok(service.update(id, body));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> update(@PathVariable String id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	//Custom Routes	
	@GetMapping("/search")
	public ResponseEntity<List<UserDTO>> indexBySearch(
			@RequestParam(value = "filter", defaultValue = "") @NotBlank String filter,
			@RequestParam(value = "page", defaultValue = "0") @Min(0) Integer page,
			@RequestParam(value = "size", defaultValue = "10") @Min(1) Integer size
	) {
		return ResponseEntity.ok().body(service.findByNameOrEmail(filter, page, size));
	}
	
	
	@GetMapping(value = "/{id}/posts")
	public User findPosts(@PathVariable String id) {
		/*User user = service.findById(id);*/
		return null;
	}
}
