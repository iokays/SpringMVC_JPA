package com.iokays.user.controller;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.iokays.security.manager.UserSecurity;
import com.iokays.user.domain.User;
import com.iokays.user.service.UserService;
import com.iokays.utils.domain.Level;
import com.iokays.utils.domain.Status;

/**
 * 用户业务控制层，处理业务请求
 * 
 * @author pengyuanbing@gmail.com
 *
 */
@Controller
@RequestMapping("/admin/user")
public class UserController {
	@Resource
	private UserService userService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(
			@PageableDefaults(pageNumber = 0, value = 50, sort = "createDate = desc")
			@RequestParam(value = "pageable", required = false) Pageable pageable,
			@RequestParam(value = "status", required = false) Status status,
			@RequestParam(value = "level", required = false) Level level, Model model) {
		if (null == pageable) {
			pageable = new PageRequest(0, 50, Direction.DESC, "createDate");
		}
		Page<User> page = userService.findAll(pageable, status, level);
		model.addAttribute("page", page);
		return "/admin/user/list";
	}
	
	@RequestMapping(value = "/load/{id}", method = RequestMethod.GET)
	public String load(@PathVariable(value = "id") String id, Model model) {
		User user = userService.findOne(id);
		model.addAttribute("user", user);
		return "/admin/user/edit";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(User user, Model model) {
		userService.insert(user);
		return "redirect:/admin/user/list";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String edit(User user, Model model) {
		userService.update(user);
		return "redirect:/admin/user/list";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable(value = "id") String id, Model model) {
		userService.delete(id);
		return "redirect:/admin/user/list";
	}
	
	public void userId() {
		@SuppressWarnings("unused")
		UserSecurity userSecurity = (UserSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}
