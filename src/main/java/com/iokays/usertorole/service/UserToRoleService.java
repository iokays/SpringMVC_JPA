package com.iokays.usertorole.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import com.iokays.usertorole.domain.UserToRole;

public interface UserToRoleService {

	public abstract void insert(UserToRole userToRole);

	public abstract void insert(Iterable<UserToRole> userToRoles);

	public abstract void delete(UserToRole userToRole);

	public abstract void delete(String id);

	public abstract void delete(String userId, String roleId);
	
	public abstract void delete(String userIds[], String roleId);
	
	public abstract void delete(String userId, String roleIds[]);
	
	public abstract Page<UserToRole> getByUserId(@Param("userId") String userId, Pageable pageable);
	
	public abstract Page<UserToRole> getByRoleId(@Param("roleId") String roleId, Pageable pageable);
}