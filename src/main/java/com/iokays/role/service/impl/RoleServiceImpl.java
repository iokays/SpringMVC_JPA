package com.iokays.role.service.impl;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iokays.role.domain.Role;
import com.iokays.role.repository.RoleRepository;
import com.iokays.role.service.RoleService;

@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {
	
	/*
	 * (non-Javadoc)
	 * @see com.iokays.role.service.RoleService#findAll(org.springframework.data.domain.Pageable)
	 */
	@Override
	public Page<Role> findAll(Pageable pageable) {
		return roleRepository.findAll(pageable);
	}

	@Override
	public Page<Role> findAllByUserId(Pageable pageable, String userId) {
		return null;
	}

	@Override
	public void insert(Role role) {
		roleRepository.save(role);
	}

	@Override
	public void update(Role role) {
		roleRepository.save(role);
	}

	@Override
	public void delete(String id) {
		roleRepository.delete(id);
	}

	@Override
	public Role findOne(String id) {
		return roleRepository.findOne(id);
	}
	
	@Resource
	private RoleRepository roleRepository;

}
