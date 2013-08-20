package com.iokays.authority.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.iokays.authority.domain.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, String> {
	@Query("select name from Authority")
	public List<String> getNames();
	
	@Query("select id from Authority")
	public List<String> getIds();
	
	@Query("select distinct t3.authority.id from UserToRole t1, Role t2, RoleToAuthority t3 "
			+ "where t1.user.id = :userId "
			+ "and t1.status = com.iokays.utils.domain.Status.enabled and t2.status = com.iokays.utils.domain.Status.enabled and t3.status = com.iokays.utils.domain.Status.enabled and "
			+ "t1.role.id = t2.id and t2.id = t3.role.id")
	public List<String> getIdsByUserId(@Param("userId")final String userId);
}
