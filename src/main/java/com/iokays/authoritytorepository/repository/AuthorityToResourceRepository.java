package com.iokays.authoritytorepository.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.iokays.authoritytorepository.domain.AuthorityToResource;

public interface AuthorityToResourceRepository extends JpaRepository<AuthorityToResource, String>{
	
	@Modifying
	@Query("delete from AuthorityToResource t1 where t1.authority.id = :authorityId and t1.resource.id = :resourceId")
	public abstract void delete(@Param("authorityId") String authorityId, @Param("resourceId") String resourceId);
	
	@Query("select t1.status, t1.resource from AuthorityToResource t1 where t1.authority.id = :authorityId")
	public abstract Page<AuthorityToResource> getByAuthorityId(@Param("authorityId") String authorityId, Pageable pageable);
	
	@Query("select t1.status, t1.authority from AuthorityToResource t1 where t1.resource.id = :resourceId")
	public abstract Page<AuthorityToResource> getByResourceId(@Param("resourceId") String resourceId, Pageable pageable);
	
}
