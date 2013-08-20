package com.iokays.user.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.iokays.user.repository.plus.UserRepositoryPlus;

/**
 * 自定义用户持久层接口(EntityManager OR JDBC)实现
 * 
 * @author pengyuanbing@gmail.com
 *
 */
public class UserRepositoryImpl implements UserRepositoryPlus {
	
	
	@PersistenceContext
	private EntityManager entityManager;
	
}
