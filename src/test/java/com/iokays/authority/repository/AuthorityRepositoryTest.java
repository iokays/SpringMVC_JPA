package com.iokays.authority.repository;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml"})
@Transactional
public class AuthorityRepositoryTest {

	@Test
	public void testGetNames() {
		authorityRepository.getNames();
	}

	@Test
	public void testGetIds() {
		authorityRepository.getIds();
	}

	public void testGetSecurityIds() {
		authorityRepository.getSecurityIds(new Sort(Sort.Direction.DESC));
	}
	
	@Resource
	private AuthorityRepository authorityRepository;

}
