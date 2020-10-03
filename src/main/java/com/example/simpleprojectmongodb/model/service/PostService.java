package com.example.simpleprojectmongodb.model.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.simpleprojectmongodb.model.Post;
import com.example.simpleprojectmongodb.model.controller.exception.ResourceNotFound;
import com.example.simpleprojectmongodb.model.dto.request.PostSaveDTO;
import com.example.simpleprojectmongodb.model.dto.request.PostUpdateDTO;
import com.example.simpleprojectmongodb.model.dto.response.PostDTO;
import com.example.simpleprojectmongodb.model.repository.PostRepository;

@Service
public class PostService {
	
	@Autowired
	private PostRepository repository;
	
	// Private methods
	private Post findAndValidateById(String id) {
		Optional<Post> obj = repository.findById(id);
		obj.orElseThrow(() -> new ResourceNotFound("não encontrado"));
		return obj.get();
	}

	// Public methods
	public List<PostDTO> findAll(int page, int size){
		return repository.findAll(UtilService.createPageRequest(page, size, Sort.Direction.ASC, "title"))
				.stream()
				.map(obj -> PostDTO.fromResource(obj))
				.collect(Collectors.toList());
	}
	
	public PostDTO findById(String id) {
		return PostDTO.fromResource(this.findAndValidateById(id));
	}
	
	public List<PostDTO> findByTitleAndBody(String filter, int page, int size){
		return repository.findByTitleAndBody(filter, UtilService.createPageRequest(page, size, Sort.Direction.ASC, "title"))
				.stream()
				.map(obj -> PostDTO.fromResource(obj))
				.collect(Collectors.toList());
	}


	public PostDTO save(PostSaveDTO body) {
		Post obj = body.toResource();
		return PostDTO.fromResource(repository.save(obj));
	}
	
	public PostDTO update(String id, PostUpdateDTO body) {
		Post obj = this.findAndValidateById(id);
		Post dto = body.toResource();
		BeanUtils.copyProperties(dto, obj, "id");
		return PostDTO.fromResource(repository.save(obj));
	}
	
	public void delete(String id) {
		repository.deleteById(this.findAndValidateById(id).getId());
	}
}
