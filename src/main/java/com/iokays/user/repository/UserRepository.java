package com.iokays.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iokays.user.domain.User;
import com.iokays.user.repository.custom.UserRepositoryCustom;

/**
 * 用户持久层 JPA接口
 * 
 * @author pengyuanbing@gmail.com
 *
 */
public interface UserRepository extends JpaRepository<User, String>, UserRepositoryCustom {

}
