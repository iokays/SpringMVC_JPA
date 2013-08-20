package com.iokays.role.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml"})
public class RoleServiceTest {
	
	@Resource
	RoleService roleService;

	@Test
	public void testFindAll() {
		roleService.findAll(new PageRequest(0, 1));
	}

	@Test
	public void testFindAllByUserId() {
		roleService.findAllByUserId(null, "1111");
	}

	@Test
	public void testFindOne() {
	}

	@Test
	public void testInsert() {
	}

	@Test
	public void testUpdate() {
	}

	@Test
	public void testDelete() {
	}

}
