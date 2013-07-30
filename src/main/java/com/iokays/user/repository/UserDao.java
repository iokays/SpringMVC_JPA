package com.iokays.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iokays.user.domain.User;

/**
 * 用户持久层 JPA接口
 * 
 * @author pengyuanbing@gmail.com
 *
 */
public interface UserDao extends JpaRepository<User, String> {

}
