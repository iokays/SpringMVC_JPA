package com.iokays.user.service.impl;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.iokays.user.domain.User;
import com.iokays.user.service.UserService;
import com.iokays.utils.domain.Level;
import com.iokays.utils.domain.Status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml"})
public class UserServiceImplTest {
	
	@Resource
	private UserService userService;
	
	public void save() {
		User user = new User();
		user.setAccount("pengyuanbing1");
		user.setName("pengyuanbing1");
		user.setDescription("说明1");
		user.setPassword("pengyuanbing1");
		user.setLevel(Level.systemic);
		user.setStatus(Status.enabled);
		userService.insert(user);
	}
	
	@Test
	public void findAll() {
		userService.findAll(null);
	}
	
	
}
