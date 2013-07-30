package com.iokays.security.repository.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import com.iokays.security.repository.UserDetailDao;
/**
 * 自定义用户持久层 权限  接口(JDBC)实现
 * @author pengyuanbing@gmail.com
 *
 */
@Repository
public class UserDetailDaoImpl implements UserDetailDao{
	
	/**
	 * 根据用户名查询用户密码 SQL语句
	 */
	private static final String sql_getPasswordByAccount = "select password from t_pub_user where account = ?";
	/**
	 * 根据用户名查询权限 SQL语句
	 */
	private static final String sql_getAuthorityByAccount = 
			"select t_pub_authoritie.id " +
			"from t_pub_user, t_pub_user_role, t_pub_role, t_pub_role_authoritie, t_pub_authoritie " +
			"where t_pub_user.account = '1' and t_pub_user.enabled = 1 and t_pub_user.id = t_pub_user_role.userId and t_pub_user_role.enabled = 1 and t_pub_user_role.roleId = t_pub_role.id and " +
			"t_pub_role.enabled = 1 and t_pub_role.id = t_pub_role_authoritie.roleId and t_pub_role_authoritie.enabled = 1 and t_pub_role_authoritie.authoritieId = t_pub_authoritie.id and t_pub_authoritie.enabled = 1 ";
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	/**
	 * 根据登录名获取用户密码
	 */
	public String getPasswordByAccount(final String account) {
		return jdbcTemplate.queryForObject(sql_getPasswordByAccount, new Object[] {account}, java.lang.String.class);
	}
	
	/**
	 * 根据用户名获取用户权限
	 */
	public Collection<GrantedAuthority> getAuthorityByUsername(final String account) {
		Collection<GrantedAuthority> grantedAuthority = new ArrayList<GrantedAuthority>();
		List<String> list = getAuthorityByAccount(account);
		
		for (int i = 0; i < list.size(); ++i) {
			GrantedAuthority authority = new SimpleGrantedAuthority(list.get(i)); 
			grantedAuthority.add(authority);
		}
		return grantedAuthority;
	}
	
	/**
	 * 根据用户名获取用户权限
	 */
	private List<String> getAuthorityByAccount(final String account) {
		List<String> result = new ArrayList<String>();
		jdbcTemplate.queryForObject(sql_getAuthorityByAccount, new Object[] {account}, java.lang.String.class);
		return result;
	}
}
	