package com.iokays.user.controller;

import javax.annotation.Resource;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import com.iokays.security.manager.UserSecurity;
import com.iokays.user.service.UserService;

/**
 * 用户业务控制层，处理业务请求
 * 
 * @author pengyuanbing@gmail.com
 *
 */
@Controller
public class UserController {
	@Resource
	private UserService userService;
	
	public void test() {
		@SuppressWarnings("unused")
		UserSecurity userSecurity = (UserSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}
