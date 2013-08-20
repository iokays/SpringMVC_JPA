package com.iokays.usertorole.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iokays.usertorole.domain.UserToRole;
import com.iokays.usertorole.repository.UserToRoleRepository;
import com.iokays.usertorole.service.UserToRoleService;

@Service("userToRoleService")
@Transactional
public class UserToRoleServiceImpl implements UserToRoleService {
	private UserToRoleRepository userToRoleRepository;
	
	/* (non-Javadoc)
	 * @see com.iokays.usertorole.service.impl.UserToRoleService#insert(com.iokays.usertorole.domain.UserToRole)
	 */
	@Override
	public void insert(UserToRole userToRole) {
		userToRoleRepository.save(userToRole);
	}
	
	/* (non-Javadoc)
	 * @see com.iokays.usertorole.service.impl.UserToRoleService#insert(java.lang.Iterable)
	 */
	@Override
	public void insert(Iterable<UserToRole> userToRoles) {
		userToRoleRepository.save(userToRoles);
	}
	
	/* (non-Javadoc)
	 * @see com.iokays.usertorole.service.impl.UserToRoleService#delete(com.iokays.usertorole.domain.UserToRole)
	 */
	@Override
	public void delete(UserToRole userToRole) {
		userToRoleRepository.delete(userToRole);
	}
	
	/* (non-Javadoc)
	 * @see com.iokays.usertorole.service.impl.UserToRoleService#delete(java.lang.String)
	 */
	@Override
	public void delete(String id) {
		userToRoleRepository.delete(id);
	}
	
	/* (non-Javadoc)
	 * @see com.iokays.usertorole.service.impl.UserToRoleService#delete(java.lang.String, java.lang.String)
	 */
	@Override
	public void delete(String userId, String roleId) {
		userToRoleRepository.delete(userId, roleId);
	}
	
	public void delete(String userIds[], String roleId) {
		for (int i = 0; i < userIds.length; ++i) {
			userToRoleRepository.delete(userIds[i], roleId);
		}
	}
	
	public void delete(String userId, String roleIds[]) {
		for (int i = 0; i < roleIds.length; ++i) {
			userToRoleRepository.delete(userId, roleIds[i]);
		}
	}
	
}
