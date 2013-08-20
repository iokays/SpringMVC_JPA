package com.iokays.authoritytorepository.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.iokays.authoritytorepository.domain.AuthorityToResource;

public interface AuthorityToResourceRepository extends JpaRepository<AuthorityToResource, String>{
	@Modifying
	@Query("delete from AuthorityToResource t1 where t1.authority.id = :authorityId and t1.resource.id = :resourceId")
	public abstract void delete(@Param("authorityId") String authorityId, @Param("resourceId") String resourceId);
}
