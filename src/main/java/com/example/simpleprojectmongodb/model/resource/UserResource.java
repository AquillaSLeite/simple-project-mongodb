package com.example.simpleprojectmongodb.model.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.simpleprojectmongodb.model.User;
import com.example.simpleprojectmongodb.model.generic.GenericResource;

@RestController
@RequestMapping("api/users")
public class UserResource extends GenericResource<User> {
	
}
