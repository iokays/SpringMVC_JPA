package com.iokays.resource.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.iokays.resource.domain.Resource;
import com.iokays.resource.repository.plus.ResourceRepositoryPlus;

public interface ResourceRepository extends JpaRepository<Resource, String>, ResourceRepositoryPlus {
	@Query("select name from Resource")
	public List<String> getNames();
	
	@Query("select id from Resource")
	public List<String> getIds();
}
