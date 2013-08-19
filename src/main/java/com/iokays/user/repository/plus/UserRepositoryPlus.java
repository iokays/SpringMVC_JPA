package com.iokays.user.repository.plus;

import java.util.List;

/**
 * 自定义用户持久层接口
 * 
 * @author pengyuanbing@gmail.com
 *
 */
public interface UserRepositoryPlus {
	public List<String> getAuthorityIdsByAccount(final String account);
}
