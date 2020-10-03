package com.example.simpleprojectmongodb.model.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class UtilService {

	static PageRequest createPageRequest(int page, int size, Sort.Direction sortDirection, String sortBy) {
		return PageRequest.of(page, size, sortDirection, sortBy);
	}
}
