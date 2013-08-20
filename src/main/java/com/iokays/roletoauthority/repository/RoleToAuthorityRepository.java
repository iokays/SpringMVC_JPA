package com.iokays.roletoauthority.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.iokays.roletoauthority.domain.RoleToAuthority;

public interface RoleToAuthorityRepository extends JpaRepository<RoleToAuthority, String>{
	@Modifying
	@Query("delete from RoleToAuthority t1 where t1.role.id = :roleId and t1.authority.id = :authorityId")
	public void delete(@Param("roleId") String roleId, @Param("authorityId") String authorityId);
}
