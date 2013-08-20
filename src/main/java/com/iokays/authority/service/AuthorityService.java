package com.iokays.authority.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.iokays.authority.domain.Authority;

public interface AuthorityService {
	public abstract List<String> getIds();
	public abstract Page<Authority> findAll(Pageable pageable);
	public abstract Authority findOne(String id);
	public abstract void insert(Authority authority);
	public abstract void update(Authority authority);
	public abstract void delete(String id);
	
}
