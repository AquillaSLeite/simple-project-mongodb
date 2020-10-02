package com.example.simpleprojectmongodb.model.generic;

public interface GenericDTO<T extends GenericModel>{
	GenericDTO<T> generateDto(T obj);
}
