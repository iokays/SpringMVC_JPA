package com.iokays.security.repository;

public interface UserDetailDao {
	public String getPasswordByAccount(final String username);
}
