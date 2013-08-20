package com.iokays.roletoauthority.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iokays.roletoauthority.domain.RoleToAuthority;
import com.iokays.roletoauthority.repository.RoleToAuthorityRepository;
import com.iokays.roletoauthority.service.RoleToAuthorityService;

@Service("roleToAuthorityService")
@Transactional
public class RoleToAuthorityServiceImpl implements RoleToAuthorityService {
	@Resource
	RoleToAuthorityRepository roleToAuthorityRepository;
	
	/* (non-Javadoc)
	 * @see com.iokays.roletoauthority.service.impl.RoleToAuthorityService#insert(com.iokays.roletoauthority.domain.RoleToAuthority)
	 */
	@Override
	public void insert(RoleToAuthority roleToAuthority) {
		roleToAuthorityRepository.save(roleToAuthority);
	}

	/* (non-Javadoc)
	 * @see com.iokays.roletoauthority.service.impl.RoleToAuthorityService#insert(java.lang.Iterable)
	 */
	@Override
	public void insert(Iterable<RoleToAuthority> roleToAuthorities) {
		roleToAuthorityRepository.save(roleToAuthorities);
	}

	/* (non-Javadoc)
	 * @see com.iokays.roletoauthority.service.impl.RoleToAuthorityService#delete(com.iokays.roletoauthority.domain.RoleToAuthority)
	 */
	@Override
	public void delete(RoleToAuthority roleToAuthority) {
		roleToAuthorityRepository.delete(roleToAuthority);
	}

	/* (non-Javadoc)
	 * @see com.iokays.roletoauthority.service.impl.RoleToAuthorityService#delete(java.lang.String)
	 */
	@Override
	public void delete(String id){
		roleToAuthorityRepository.delete(id);
	}

	/* (non-Javadoc)
	 * @see com.iokays.roletoauthority.service.impl.RoleToAuthorityService#delete(java.lang.String, java.lang.String)
	 */
	@Override
	public void delete(String roleId, String authorityId){
		roleToAuthorityRepository.delete(roleId, authorityId);
	}
	
	/* (non-Javadoc)
	 * @see com.iokays.roletoauthority.service.impl.RoleToAuthorityService#delete(java.lang.String[], java.lang.String)
	 */
	@Override
	public void delete(String[] roleIds, String authorityId){
		for (int i = 0; i < roleIds.length; ++i) {
			roleToAuthorityRepository.delete(roleIds[i], authorityId);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.iokays.roletoauthority.service.impl.RoleToAuthorityService#delete(java.lang.String, java.lang.String[])
	 */
	@Override
	public void delete(String roleId, String[] authorityIds){
		for (int i = 0; i < authorityIds.length; ++i) {
			roleToAuthorityRepository.delete(roleId, authorityIds[i]);
		}
	}
}
