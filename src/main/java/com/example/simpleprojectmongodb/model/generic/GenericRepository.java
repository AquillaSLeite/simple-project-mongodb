package com.example.simpleprojectmongodb.model.generic;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenericRepository<T extends GenericModel> extends MongoRepository<T, String>{

}
