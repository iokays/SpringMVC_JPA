package com.iokays.authoritytorepository.service.impl;

import javax.annotation.Resource;

import com.iokays.authoritytorepository.domain.AuthorityToResource;
import com.iokays.authoritytorepository.repository.AuthorityToResourceRepository;
import com.iokays.authoritytorepository.service.AuthorityToResourceService;

public class AuthorityToResourceServiceImpl implements AuthorityToResourceService {
	@Resource
	AuthorityToResourceRepository authorityToResourceRepository;
	
	/* (non-Javadoc)
	 * @see com.iokays.authoritytorepository.service.impl.AuthorityToResourceService#insert(com.iokays.authoritytorepository.domain.AuthorityToResource)
	 */
	@Override
	public void insert(AuthorityToResource authorityToResource) {
		authorityToResourceRepository.save(authorityToResource);
	}

	/* (non-Javadoc)
	 * @see com.iokays.authoritytorepository.service.impl.AuthorityToResourceService#insert(java.lang.Iterable)
	 */
	@Override
	public void insert(Iterable<AuthorityToResource> authorityToResources) {
		authorityToResourceRepository.save(authorityToResources);
	}

	/* (non-Javadoc)
	 * @see com.iokays.authoritytorepository.service.impl.AuthorityToResourceService#delete(com.iokays.authoritytorepository.domain.AuthorityToResource)
	 */
	@Override
	public void delete(AuthorityToResource authorityToResource) {
		authorityToResourceRepository.delete(authorityToResource);
	}

	/* (non-Javadoc)
	 * @see com.iokays.authoritytorepository.service.impl.AuthorityToResourceService#delete(java.lang.String)
	 */
	@Override
	public void delete(String id) {
		authorityToResourceRepository.delete(id);
	}

	/* (non-Javadoc)
	 * @see com.iokays.authoritytorepository.service.impl.AuthorityToResourceService#delete(java.lang.String, java.lang.String)
	 */
	@Override
	public void delete(String authorityId, String resourceId) {
		authorityToResourceRepository.delete(authorityId, resourceId);
	}

	/* (non-Javadoc)
	 * @see com.iokays.authoritytorepository.service.impl.AuthorityToResourceService#delete(java.lang.String[], java.lang.String)
	 */
	@Override
	public void delete(String[] authorityIds, String resourceId) {
		for (int i = 0; i < authorityIds.length; ++i) {
			authorityToResourceRepository.delete(authorityIds[i], resourceId);
		}
	}

	/* (non-Javadoc)
	 * @see com.iokays.authoritytorepository.service.impl.AuthorityToResourceService#delete(java.lang.String, java.lang.String[])
	 */
	@Override
	public void delete(String authorityId, String[] resourceIds) {
		for (int i = 0; i < resourceIds.length; ++i) {
			authorityToResourceRepository.delete(authorityId, resourceIds[i]);
		}
	}
}
