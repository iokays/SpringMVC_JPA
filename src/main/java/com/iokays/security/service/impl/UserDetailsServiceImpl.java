package com.iokays.security.service.impl;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.iokays.user.repository.UserRepository;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService{
	
	/**
	 * 根据用户名获取密码，权限
	 */
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		final String password = userDao.getPasswordByAccount(username);
		
		GrantedAuthorityImpl auth2=new GrantedAuthorityImpl("ROLE_ADMIN");
		grantedAuthorities.add(auth2);
		
		return new User(username, password, true, true, true, true, grantedAuthorities);
	}
	
	@Resource
	UserRepository userDao;

	
}
