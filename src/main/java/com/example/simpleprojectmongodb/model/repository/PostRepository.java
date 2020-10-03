package com.example.simpleprojectmongodb.model.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.simpleprojectmongodb.model.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{

}