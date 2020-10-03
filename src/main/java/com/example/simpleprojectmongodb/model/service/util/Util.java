package com.example.simpleprojectmongodb.model.service.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class Util {

	public PageRequest createPageRequest(int page, int size, Sort.Direction sortDirection, String sortBy) {
		return PageRequest.of(page, size, sortDirection, sortBy);
	}
}
