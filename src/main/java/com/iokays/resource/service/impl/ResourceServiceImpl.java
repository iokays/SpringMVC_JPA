package com.iokays.resource.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.iokays.resource.domain.Resource;
import com.iokays.resource.repository.ResourceRepository;
import com.iokays.resource.service.ResourceService;

@Service("resourceService")
public class ResourceServiceImpl implements ResourceService {
	
	@javax.annotation.Resource
	ResourceRepository resourceRepository;

	@Override
	public Page<Resource> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return resourceRepository.findAll(pageable);
	}

	@Override
	public Resource findOne(String id) {
		// TODO Auto-generated method stub
		return resourceRepository.findOne(id);
	}

	@Override
	public void insert(Resource resource) {
		// TODO Auto-generated method stub
		resourceRepository.save(resource);
	}

	@Override
	public void update(Resource resource) {
		// TODO Auto-generated method stub
		resourceRepository.save(resource);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		resourceRepository.delete(id);
	}

}
