package com.iokays.usertorole.service;

import com.iokays.usertorole.domain.UserToRole;

public interface UserToRoleService {

	public abstract void insert(UserToRole userToRole);

	public abstract void insert(Iterable<UserToRole> userToRoles);

	public abstract void delete(UserToRole userToRole);

	public abstract void delete(String id);

	public abstract void delete(String userId, String roleId);
	
	public abstract void delete(String userIds[], String roleId);
	
	public abstract void delete(String userId, String roleIds[]);

}