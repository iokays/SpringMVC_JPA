package com.iokays.roletoauthority.service;

import com.iokays.roletoauthority.domain.RoleToAuthority;

public interface RoleToAuthorityService {

	public abstract void insert(RoleToAuthority roleToAuthority);

	public abstract void insert(Iterable<RoleToAuthority> roleToAuthorities);

	public abstract void delete(RoleToAuthority roleToAuthority);

	public abstract void delete(String id);

	public abstract void delete(String roleId, String authorityId);

	public abstract void delete(String[] roleIds, String authorityId);

	public abstract void delete(String roleId, String[] authorityIds);

}