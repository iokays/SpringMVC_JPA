package com.iokays.user.service.impl;

import java.util.List;

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
public class UserServiceImpl implements UserService {
	@Resource
	private UserRepository userRepository;

	/* (non-Javadoc)
	 * @see com.iokays.user.service.UserService#save(com.iokays.user.domain.User)
	 */
	@Override
	public void save(User user) {
		userRepository.save(user);
	}
	
	/* (non-Javadoc)
	 * @see com.iokays.user.service.UserService#findOne(java.lang.String)
	 */
	@Override
	public User findOne(String id) {
		return userRepository.findOne(id);
	}
	
	/* (non-Javadoc)
	 * @see com.iokays.user.service.UserService#delete(java.lang.String)
	 */
	@Override
	public void delete(String id) {
		userRepository.delete(id);
	}
	
	/* (non-Javadoc)
	 * @see com.iokays.user.service.UserService#delete(java.lang.String)
	 */
	@Override
	public void delete(String... ids) {
		for (int i = 0; i < ids.length; ++i) {
			userRepository.delete(ids[i]);
		}
	}
	/* (non-javadoc)
	 * 	@see com.iokays.user.service.UserService#findAll()
	 * 
	 */
	@Override
	public List<User> findAll() {
		return userRepository.findAll(); 
	}
}
