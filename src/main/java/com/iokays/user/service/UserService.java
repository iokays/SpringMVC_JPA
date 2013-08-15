package com.iokays.user.service;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.iokays.user.domain.User;
/**
 * 用户操作业务逻辑层接口
 * 
 * @author pengyuanbing@gmail.com
 *
 */
@Transactional
public interface UserService {
	
	/**
	 * 分页查询用户列表
	 * @param
	 *    pageable 分页参数
	 * @return
	 *    Page<User> 查询到的User对象列表【getContent】
	 */
//	public abstract Page<User> findAll(Pageable pageable);
	
	/**
	 * 分页查询用户列表
	 * @param
	 *    pageable 分页参数
	 *    status 对象禁用状态
	 *    
	 * @return
	 *    Page<User> 查询到的User对象列表【getContent】
	 */
//	public abstract Page<User> findAll(Pageable pageable, Status status);
	
	/**
	 * 分页查询用户列表
	 * @param
	 *    pageable 分页参数
	 *    level 系统权限级别
	 *    
	 * @return
	 *    Page<User> 查询到的User对象列表【getContent】
	 */
//	public abstract Page<User> findAll(Pageable pageable, Level level);
	
	/**
	 * 分页查询用户列表
	 * @param
	 *    pageable 分页参数
	 *    status 对象禁用状态
	 *    level 系统权限级别
	 *    
	 * @return
	 *    Page<User> 查询到的User对象列表【getContent】
	 */
//	public abstract Page<User> findAll(Pageable pageable, Status status, Level level);
	
	/**
	 * 保存，更新用户对象
	 * @param 
	 *    user 用户的基本信息
	 */
	public abstract void save(User user);
	
	/**
	 * 根据用户ID获取用户的基本信息
	 * @param
	 *    id 用户ID
	 * @return 
	 *    User 用户基本信息
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public abstract User findOne(String id);
	
	/**
	 * 根据用户ID单个删除用户基本信息 
	 * @param
	 *    id 用户ID
	 */
	public abstract void delete(String id);
	
	/**
	 * 根据用户ID批量删除用户基本信息 
	 * @param
	 *    ids 用户Id数组
	 */
	public abstract void delete(String... ids);
	
	/**
	 * 查询所有用户列表
	 * @return
	 *    List<User> 用户列表
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public abstract List<User> findAll();
}
