package com.iokays.security.manager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.iokays.user.repository.UserRepository;

@Service("userDetailsManager")
public class UserDetailsManager implements UserDetailsService {
	
	/**
	 * 根据用户名获取密码，权限
	 */
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		final String password = userRepository.getPasswordByAccount(username);
		final List<String> list = userRepository.getAuthorityIdsByAccount(username);
		
		Collection<GrantedAuthority> grantedAuthorities =  new ArrayList<GrantedAuthority>();
		for (int i = 0; i < list.size(); ++i) {
			GrantedAuthority authority = new SimpleGrantedAuthority(list.get(i)); 
			grantedAuthorities.add(authority);
		}
		
		return new UserSecurity(null, username, password, grantedAuthorities);
	}
	
	@Resource
	UserRepository userRepository;
}
