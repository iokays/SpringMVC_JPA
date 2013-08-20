package com.iokays.roletoauthority.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import com.iokays.roletoauthority.domain.RoleToAuthority;

public interface RoleToAuthorityService {

	public abstract void insert(RoleToAuthority roleToAuthority);

	public abstract void insert(Iterable<RoleToAuthority> roleToAuthorities);

	public abstract void delete(RoleToAuthority roleToAuthority);

	public abstract void delete(String id);

	public abstract void delete(String roleId, String authorityId);

	public abstract void delete(String[] roleIds, String authorityId);

	public abstract void delete(String roleId, String[] authorityIds);
	
	public abstract Page<RoleToAuthority> getByRoleId(@Param("roleId") String roleId, Pageable pageable);
	
	public abstract Page<RoleToAuthority> getByAuthorityId(@Param("authorityId") String authorityId, Pageable pageable);

}