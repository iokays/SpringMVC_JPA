package com.iokays.user.repository.custom;

import java.util.List;

/**
 * 自定义用户持久层接口
 * 
 * @author pengyuanbing@gmail.com
 *
 */
public interface UserRepositoryCustom {
	public List<String> getAuthoritiesByAccount(final String account);
	public String getPasswordByAccount(final String account);
}
