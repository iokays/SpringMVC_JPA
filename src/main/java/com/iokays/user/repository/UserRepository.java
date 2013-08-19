package com.iokays.user.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.iokays.user.domain.User;
import com.iokays.user.repository.plus.UserRepositoryPlus;
import com.iokays.utils.domain.Level;
import com.iokays.utils.domain.Status;

/**
 * 用户持久层 JPA接口
 * 
 * @author pengyuanbing@gmail.com
 *
 */
public interface UserRepository extends JpaRepository<User, String>, UserRepositoryPlus {
	
	/**
	 * 根据User对象状态查询用户列表
	 * 
	 * @param pageable 分页参数
	 * @param status 用户状态	
	 * @return
	 */
	public abstract Page<User> findAllByStatus(Pageable pageable, Status status);
	
	/**
	 * 根据User对象级别查询用户列表
	 * 
	 * @param pageable 分页参数
	 * @param level 用户级别
	 * @return
	 */
	public abstract Page<User> findAllByLevel(Pageable pageable, Level level);
	
	/**
	 * 根据User对象级别, 状态查询用户列表
	 * 
	 * @param pageable 分页参数
	 * @param status  用户状态
	 * @param level 用户级别
	 * @return
	 */
	public abstract Page<User> findAllByLevelAndStatus(Pageable pageable, Status status, Level level);
	
	/**
	 * 根据用户ID查询用户密码
	 * 
	 * @param id 用户唯一标识符
	 * @return 用户密码
	 */
	@Query("select t1.password from User as t1 where t1.id = :id")
	public abstract String getPassword(@Param("id") final String id);
	
	/**
	 * 根据用户ID查询用户密码
	 * 
	 * @param account 用户登录用户名【具备唯一型】
	 * @return 用户密码
	 */
	@Query("select t1.password from User as t1 where t1.account = :account")
	public abstract String getPasswordByAccount(@Param("account") final String account);
	
	/**
	 * 根据用户登录用户名查询用户ID
	 * 
	 * @param account 用户登录用户名【具备唯一型】
	 * @return 用户ID
	 */
	@Query("select t1.id from User as t1 where t1.account = :account")
	public abstract String getId(@Param("account") final String account);
	
}
