package com.iokays.roletoauthority.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.iokays.roletoauthority.domain.RoleToAuthority;

public interface RoleToAuthorityRepository extends JpaRepository<RoleToAuthority, String>{
	@Modifying
	@Query("delete from RoleToAuthority t1 where t1.role.id = :roleId and t1.authority.id = :authorityId")
	public void delete(@Param("roleId") String roleId, @Param("authorityId") String authorityId);
	
	@Query("select t1.status, t1.authority from RoleToAuthority t1 where t1.role.id = :roleId")
	public abstract Page<RoleToAuthority> getByRoleId(@Param("roleId") String roleId, Pageable pageable);
	
	@Query("select t1.status, t1.role from RoleToAuthority t1 where t1.authority.id = :authorityId")
	public abstract Page<RoleToAuthority> getByAuthorityId(@Param("authorityId") String authorityId, Pageable pageable);
}
