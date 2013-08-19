package com.iokays.usertorole.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iokays.usertorole.domain.UserToRole;

public interface UserToRoleRepository extends JpaRepository<UserToRole, String> {
	
}
