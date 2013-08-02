package com.iokays.resource.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.iokays.resource.domain.Resource;
import com.iokays.resource.repository.custom.ResourceRepositoryCustom;

public interface ResourceRepository extends JpaRepository<Resource, String>, ResourceRepositoryCustom{
	@Query("select name from Authority")
	public List<String> getName();
	
	@Query("select id from Authority")
	public List<String> getId();
}
