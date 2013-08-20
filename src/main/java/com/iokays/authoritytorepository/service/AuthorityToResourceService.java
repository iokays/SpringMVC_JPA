package com.iokays.authoritytorepository.service;

import com.iokays.authoritytorepository.domain.AuthorityToResource;

public interface AuthorityToResourceService {

	public abstract void insert(AuthorityToResource authorityToResource);

	public abstract void insert(Iterable<AuthorityToResource> authorityToResources);

	public abstract void delete(AuthorityToResource authorityToResource);

	public abstract void delete(String id);

	public abstract void delete(String authorityId, String resourceId);

	public abstract void delete(String[] authorityIds, String resourceId);

	public abstract void delete(String authorityId, String[] resourceIds);

}