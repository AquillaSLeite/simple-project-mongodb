package com.example.simpleprojectmongodb.model.generic;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.simpleprojectmongodb.model.resource.exception.ResourceNotFound;

public class GenericService<T extends GenericModel> {

	@Autowired
	private GenericRepository<T> repository;

	public List<T> findAll(){
		return repository.findAll();
	}
	
	public T findById(String id) {
		Optional<T> recoverT = repository.findById(id);
		recoverT.orElseThrow(() -> new ResourceNotFound("não encontrado"));
		return recoverT.get();
	}

	public T save(T obj) {
		return repository.save(obj);
	}
	
	public void delete(String id) {
		Optional<T> recoverT = repository.findById(id);
		recoverT.orElseThrow(() -> new ResourceNotFound("não encontrado"));
		repository.delete(recoverT.get());
	}
}
