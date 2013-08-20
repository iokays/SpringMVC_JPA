package com.iokays.resource.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.iokays.resource.repository.plus.ResourceRepositoryPlus;

public class ResourceRepositoryImpl implements ResourceRepositoryPlus {
	
	@PersistenceContext
	private EntityManager entityManager;
	
}
