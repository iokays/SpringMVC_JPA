package com.iokays.resource.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.iokays.resource.domain.Resource;

public interface ResourceService {
	public abstract Page<Resource> findAll(Pageable pageable);
	public abstract Resource findOne(String id);
	public abstract void insert(Resource resource);
	public abstract void update(Resource resource);
	public abstract void delete(String id);
}
