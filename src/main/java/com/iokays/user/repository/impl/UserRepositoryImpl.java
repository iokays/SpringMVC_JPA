package com.iokays.user.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.iokays.user.repository.plus.UserRepositoryPlus;

/**
 * 自定义用户持久层接口(EntityManager OR JDBC)实现
 * 
 * @author pengyuanbing@gmail.com
 *
 */
public class UserRepositoryImpl implements UserRepositoryPlus {
	
	/**
	 * 根据用户名查询权限 SQL语句
	 */
	private static final String sql_getAuthorityIdsByAccount = 
			"select t_pub_authority.id_ " +
			"from t_pub_user, t_pub_user_role, t_pub_role, t_pub_role_authority, t_pub_authority " +
			"where t_pub_user.account_ = ? and t_pub_user.status_ = 1 and t_pub_user.id_ = t_pub_user_role.user_id_ and t_pub_user_role.status_ = 1 and t_pub_user_role.role_id_ = t_pub_role.id_ and " +
			"t_pub_role.status_ = 1 and t_pub_role.id_ = t_pub_role_authority.role_id_ and t_pub_role_authority.status_ = 1 and t_pub_role_authority.authority_id_ = t_pub_authority.id_ and t_pub_authority.status_ = 1 ";
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	/**
	 * 根据用户名获取用户权限ID
	 */
	public List<String> getAuthorityIdsByAccount(final String account) {
		return jdbcTemplate.query(sql_getAuthorityIdsByAccount, new Object[] {account}, new ParameterizedRowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString(1);
			}
		});
	}
	
}
