package com.example.simpleprojectmongodb.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.simpleprojectmongodb.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{

	@Query("{'email': {$regex: ?0, $options: 'i'}}")
	User findByEmail(String email);
	
	@Query("{ $or: [ {'name': {$regex: ?0, $options: 'i'}}, {'email': {$regex: ?0, $options: 'i'}} ] }")
	Page<User> findByNameOrEmail(String filter, Pageable pageable);
}