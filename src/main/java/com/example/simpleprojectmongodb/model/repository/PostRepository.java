package com.example.simpleprojectmongodb.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.simpleprojectmongodb.model.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{
	
	@Query("{ $or: [ {'title': {$regex: ?0, $options: 'i'}}, {'body': {$regex: ?0, $options: 'i'}} ] }")
	Page<Post> findByTitleAndBody(String filter, Pageable pageable);
}