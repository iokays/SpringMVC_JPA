package com.iokays.usertorole.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.iokays.usertorole.domain.UserToRole;

public interface UserToRoleRepository extends JpaRepository<UserToRole, String> {
	
	@Modifying
	@Query("delete from UserToRole t1 where t1.user.id = :userId and t1.role.id = :roleId")
	public abstract void delete(@Param("userId") String userId, @Param("roleId") String roleId);
	
	@Query("select t1.status, t1.role from UserToRole t1 where t1.user.id = :userId")
	public abstract Page<UserToRole> getByUserId(@Param("userId") String userId, Pageable pageable);
	
	@Query("select t1.status, t1.user from UserToRole t1 where t1.user.id = :roleId")
	public abstract Page<UserToRole> getByRoleId(@Param("roleId") String roleId, Pageable pageable);
	
}
