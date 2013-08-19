package com.iokays.role.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.iokays.role.domain.Role;

public interface RoleRepository extends JpaRepository<Role, String> {
	@Query("select t1 from Role t1, UserToRole t2 where t1.id = t2.role.id and t2.user.id =:userId")
	public abstract List<Role> getRolesByUserId(@Param("userId") String userId);
	
}
