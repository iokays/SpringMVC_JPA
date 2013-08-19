package com.iokays.user.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.iokays.user.domain.User;
import com.iokays.utils.domain.Level;
import com.iokays.utils.domain.Status;
/**
 * 用户操作业务逻辑层接口
 * 
 * @author pengyuanbing@gmail.com
 *
 */
public interface UserService {

	/**
	 * 分页查询用户列表[默认以创建时间倒序排列]
	 * 
	 * @param pageable 分页参数
	 * @return  Page<User> 查询到的User对象列表【getContent】
	 */
	public abstract Page<User> findAll(Pageable pageable);
	
	/**
	 * 分页查询用户列表
	 * 
	 * @param pageable 分页参数
	 * @param status 对象禁用状态
	 * @return Page<User> 查询到的User对象列表【getContent】
	 */
	public abstract Page<User> findAll(Pageable pageable, Status status);
	
	/**
	 * 分页查询用户列表
	 * 
	 * @param pageable 分页参数
	 * @param level 系统权限级别
	 * 
	 * @return Page<User> 查询到的User对象列表【getContent】
	 */
	public abstract Page<User> findAll(Pageable pageable, Level level);
	
	/**
	 * 分页查询用户列表
	 * 
	 * @param pageable 分页参数
	 * @param status 对象禁用状态
	 * @param level 系统权限级别
	 * @return Page<User> 查询到的User对象列表【getContent】
	 */
	
	public abstract Page<User> findAll(Pageable pageable, Status status, Level level);
	
	/**
	 * 分页查询用户列表
	 * 
	 * @param pageable 分页参数
	 * @param status 对象禁用状态
	 * @param level 系统权限级别   
	 * @return Page<User> 查询到的User对象列表【getContent】
	 */
//	public abstract Page<User> findAll(Pageable pageable, Status status, Level level);
	
	/**
	 * 保存，更新用户对象
	 * 
	 * @param user 用户的基本信息
	 */
	public abstract void insert(User user);
	
	/**
	 * 保存，更新用户对象
	 * 
	 * @param user 用户的基本信息
	 */
	public abstract void update(User user);
	
	/**
	 * 根据用户ID获取用户的基本信息
	 * 
	 * @param id 用户ID
	 * @return User 用户基本信息
	 */
	public abstract User findOne(String id);
	
	/**
	 * 根据用户ID单个删除用户基本信息 
	 * @param id 用户ID
	 */
	public abstract void delete(String id);
	
	/**
	 * 根据用户ID批量删除用户基本信息 
	 * @param ids 用户Id数组
	 */
	public abstract void delete(String... ids);
	
	
}
