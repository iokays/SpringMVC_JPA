package com.iokays.user.service.impl;

import javax.annotation.Resource;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.iokays.user.domain.User;
import com.iokays.user.repository.UserRepository;
import com.iokays.user.service.UserService;
import com.iokays.utils.domain.Level;
import com.iokays.utils.domain.Status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:applicationContext-security.xml"})
@Transactional
public class UserServiceImplTest {
	
	@Resource
	private UserService userService;
	
	@Resource
	private UserRepository userRepository;
	
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
	
	public void findAll() {
		userService.findAll(null);
	}
	
}
