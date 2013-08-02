package com.iokays.authority.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.iokays.authority.domain.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, String> {
	@Query("select name from Authority")
	public List<String> getNames();
	
	@Query("select id from Authority")
	public List<String> getIds();
}
