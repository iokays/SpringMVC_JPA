package com.iokays.user.service.impl;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.iokays.user.domain.User;
import com.iokays.user.repository.UserRepository;
import com.iokays.user.service.UserService;
import com.iokays.utils.domain.Level;
import com.iokays.utils.domain.Status;

/**
 * 用户操作业务逻辑层实现
 * 
 * @author pengyuanbing@gmail.com
 *
 */
@Service("userService")
@Transactional(readOnly = true, timeout = 10)
public class UserServiceImpl implements UserService {
	@Resource
	private UserRepository userRepository;
	
	/*
	 * (non-Javadoc)
	 * @see com.iokays.user.service.UserService#findAll(org.springframework.data.domain.Pageable)
	 */
	@Override
	public Page<User> findAll(Pageable pageable) {
		return userRepository.findAll(pageable);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.iokays.user.service.UserService#findAll(org.springframework.data.domain.Pageable, com.iokays.utils.domain.Status)
	 */
	@Override
	public Page<User> findAll(Pageable pageable, Status status) {
		return null == status ? findAll(pageable) : userRepository.findAllByStatus(pageable, status);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.iokays.user.service.UserService#findAll(org.springframework.data.domain.Pageable, com.iokays.utils.domain.Level)
	 */
	@Override
	public Page<User> findAll(Pageable pageable, Level level) {
		return null == level ? findAll(pageable) : userRepository.findAllByLevel(pageable, level);
	}
	
	@Override
	public Page<User> findAll(Pageable pageable, Status status, Level level) {
		if (null == status) {
			return findAll(pageable, level);
		} else {
			if (null == level) {
				return findAll(pageable, status);
			} else {
				return userRepository.findAllByLevelAndStatus(pageable, status, level);
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.iokays.user.service.UserService#save(com.iokays.user.domain.User)
	 */
	@Override
	@Transactional(readOnly = false, propagation=Propagation.REQUIRES_NEW) 
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
	@Transactional(readOnly = false, propagation=Propagation.REQUIRES_NEW) 
	public void delete(String id) {
		userRepository.delete(id);
	}
	
	/* (non-Javadoc)
	 * @see com.iokays.user.service.UserService#delete(java.lang.String)
	 */
	@Override
	@Transactional(readOnly = false, propagation=Propagation.REQUIRES_NEW) 
	public void delete(String... ids) {
		for (int i = 0; i < ids.length; ++i) {
			userRepository.delete(ids[i]);
		}
	}
	
}
