package com.iokays.resource.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.iokays.resource.domain.Resource;
import com.iokays.resource.repository.plus.ResourceRepositoryPlus;

public interface ResourceRepository extends JpaRepository<Resource, String>, ResourceRepositoryPlus {
	@Query("select name from Resource")
	public List<String> getNames();
	
	@Query("select id from Resource")
	public List<String> getIds();
	
	@Query("select t1.value from Resource t1, AuthorityToResource t2 where t1.id = t2.resource.id and t2.authority.id = :authorityId")
	public List<String> getValuesByAuthorityId(@Param("authorityId") String authorityId);
}
