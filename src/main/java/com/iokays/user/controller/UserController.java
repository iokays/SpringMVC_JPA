package com.iokays.user.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

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
	
}
