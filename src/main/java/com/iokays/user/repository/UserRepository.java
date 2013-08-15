package com.iokays.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.iokays.user.domain.User;
import com.iokays.user.repository.custom.UserRepositoryCustom;

/**
 * 用户持久层 JPA接口
 * 
 * @author pengyuanbing@gmail.com
 *
 */
public interface UserRepository extends JpaRepository<User, String>, UserRepositoryCustom {
	
	/**
	 * 根据用户ID查询用户密码
	 * @param
	 *    id 用户唯一标识符
	 * @return
	 *    用户密码
	 */
	@Query("select t1.password from User as t1 where t1.id = :id")
	public abstract String getPassword(@Param("id") final String id);
	
	/**
	 * 根据用户ID查询用户密码
	 * @param
	 *    account 用户登录用户名【具备唯一型】
	 * @return
	 *    用户密码
	 */
	@Query("select t1.password from User as t1 where t1.account = :account")
	public abstract String getPasswordByAccount(@Param("account") final String account);
	
	/**
	 * 根据用户登录用户名查询用户ID
	 * @param
	 *    account 用户登录用户名【具备唯一型】
	 * @return
	 *    用户ID
	 */
	@Query("select t1.id from User as t1 where t1.account = :account")
	public abstract String getId(@Param("account") final String account);
	
}
