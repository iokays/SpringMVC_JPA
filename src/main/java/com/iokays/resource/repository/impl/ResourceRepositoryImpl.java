package com.iokays.resource.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.iokays.resource.repository.custom.ResourceRepositoryCustom;

public class ResourceRepositoryImpl implements ResourceRepositoryCustom {
	
	private static String GET_VALUE_BY_AUTHORITY_ID_SQL =  "select t2.value_ "
			+ "from t_pub_authority_resource t1,  t_pub_resource t2 "
			+ "where t1.resource_id_ = t2.id_ and t1.authority_id_ = ?";
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<String> getValues(String authorityId) {
		return jdbcTemplate.query(GET_VALUE_BY_AUTHORITY_ID_SQL, new Object[] {authorityId}, new ParameterizedRowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString(1);
			}
		});
	}
}
