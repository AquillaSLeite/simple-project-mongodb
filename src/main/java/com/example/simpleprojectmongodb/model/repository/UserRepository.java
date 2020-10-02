package com.example.simpleprojectmongodb.model.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.simpleprojectmongodb.model.User;
import com.example.simpleprojectmongodb.model.generic.GenericRepository;

@Repository
public interface UserRepository extends GenericRepository<User> {

	@Query("{ 'email': { $regex: ?0, $options: 'i' } }")
	User findByEmail(String email);
}
