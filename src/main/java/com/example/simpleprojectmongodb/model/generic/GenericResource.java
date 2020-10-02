package com.example.simpleprojectmongodb.model.generic;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class GenericResource<T extends GenericModel> {

	@Autowired
	private GenericService<T> service;
	
	@Autowired
	private GenericDTO<T> genericDto;
	private GenericDTO<T> generateDto(T generics) {
		return genericDto.generateDto(generics);
	}

	@GetMapping
	public ResponseEntity<List<GenericDTO<T>>> index() {
		List<T> findObjects = service.findAll();
		List<GenericDTO<T>> listGenericDto = new ArrayList<GenericDTO<T>>();		
		for(T generics : findObjects) {
			listGenericDto.add(generateDto(generics));
		}		
		return ResponseEntity.ok().body(listGenericDto);
	}
	
	@PostMapping
	public ResponseEntity<GenericDTO<T>> store(@Valid @RequestBody T obj){
		obj = service.save(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(generateDto(obj));
	}
	
	@GetMapping(value = "/{id}")
	public GenericDTO<T> show(@PathVariable String id){
		return generateDto(service.findById(id));
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<GenericDTO<T>> update(@PathVariable String id,@Valid @RequestBody T entity){
		T objT =  service.findById(id);
		BeanUtils.copyProperties(entity, objT, "id");
		return ResponseEntity.ok(generateDto(service.save(objT)));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<T> update(@PathVariable String id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
