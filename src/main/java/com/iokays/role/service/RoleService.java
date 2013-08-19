package com.iokays.role.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.iokays.role.domain.Role;

public interface RoleService {
	public abstract Page<Role> findAll(Pageable pageable);
	public abstract Page<Role> findAllByUserId(Pageable pageable, String userId);
	public abstract Role findOne(String id);
	public abstract void insert(Role role);
	public abstract void update(Role role);
	public abstract void delete(String id);
}
