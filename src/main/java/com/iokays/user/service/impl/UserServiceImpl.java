package com.iokays.user.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.iokays.user.domain.User;
import com.iokays.user.repository.UserRepository;
import com.iokays.user.service.UserService;

/**
 * 用户操作业务逻辑层实现
 * 
 * @author pengyuanbing@gmail.com
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService{
	@Resource
	private UserRepository userRepository;
	
	public void save(User user) {
		userRepository.save(user);
	}
}
