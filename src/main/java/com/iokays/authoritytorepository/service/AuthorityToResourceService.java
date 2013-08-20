package com.iokays.authoritytorepository.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import com.iokays.authoritytorepository.domain.AuthorityToResource;

public interface AuthorityToResourceService {

	public abstract void insert(AuthorityToResource authorityToResource);

	public abstract void insert(Iterable<AuthorityToResource> authorityToResources);

	public abstract void delete(AuthorityToResource authorityToResource);

	public abstract void delete(String id);

	public abstract void delete(String authorityId, String resourceId);

	public abstract void delete(String[] authorityIds, String resourceId);

	public abstract void delete(String authorityId, String[] resourceIds);
	
	public abstract Page<AuthorityToResource> getByAuthorityId(@Param("authorityId") String authorityId, Pageable pageable);
	
	public abstract Page<AuthorityToResource> getByResourceId(@Param("resourceId") String resourceId, Pageable pageable);
	

}