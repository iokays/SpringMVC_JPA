package com.iokays.user.repository.custom;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

/**
 * 自定义用户持久层接口
 * 
 * @author pengyuanbing@gmail.com
 *
 */
public interface UserRepositoryCustom {
	public Collection<GrantedAuthority> getAuthorityByUsername(final String account);
	public String getPasswordByAccount(final String account);
}
