package com.iokays.security.service.impl;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.iokays.security.repository.UserDetailDao;

@Service("userDetailsService")
public class UserDetailServiceImpl implements UserDetailsService{
	
	@Resource
	UserDetailDao userDetailDao;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		Collection<GrantedAuthority> grantedAuthority = new ArrayList<GrantedAuthority>();
		final String password = userDetailDao.getPasswordByAccount(username);
		return new User(username, password, true, true, true, true, grantedAuthority);
	}
}
