package com.iokays.user.service;

import com.iokays.user.domain.User;
/**
 * 用户操作业务逻辑层接口
 * 
 * @author pengyuanbing@gmail.com
 *
 */
public interface UserService {

	/**
	 * 保存，修改用户基本信息
	 * @param user
	 * 			    用户基本信息
	 */
	public void save(User user);
}
