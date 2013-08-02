package com.iokays.authority.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.iokays.authority.repository.AuthorityRepository;
import com.iokays.authority.service.AuthorityService;

/**
 * 
 * 
 * @author pengyuanbing@gmail.com
 *
 */

@Service("authorityService")
public class AuthorityServiceImpl implements AuthorityService {
	
	public List<String> getId() {
		return authorityRepository.getIds();
	}
	
	@Resource
	private AuthorityRepository authorityRepository;
}
