package com.iokays.usertorole.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.iokays.usertorole.domain.UserToRole;

public interface UserToRoleRepository extends JpaRepository<UserToRole, String> {
	
	@Modifying
	@Query("delete from UserToRole t1 where t1.user.id = :userId and t1.role.id = :roleId")
	public abstract void delete(@Param("userId") String userId,@Param("roleId") String roleId);
	
}
