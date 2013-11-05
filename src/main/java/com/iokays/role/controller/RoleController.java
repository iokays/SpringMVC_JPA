package com.iokays.role.controller;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.iokays.role.domain.Role;
import com.iokays.role.service.RoleService;

@Controller
@RequestMapping("/admin/role")
public class RoleController {
	@Resource
	private RoleService roleService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(@PageableDefaults(pageNumber = 0, value = 50, sort = "createDate", sortDir=Direction.DESC)
	@RequestParam(value = "pageable", required = false) Pageable pageable, Model model) {
		Page<Role> page = roleService.findAll(pageable);
		model.addAttribute("page", page);
		return "/admin/role/list";
	}
	
	@RequestMapping(value = "/load/{id}", method = RequestMethod.GET)
	public String load(@PathVariable(value = "id") String id, Model model) {
		Role role = roleService.findOne(id);
		model.addAttribute("role", role);
		return "/admin/role/edit";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(Role role, Model model) {
		roleService.insert(role);
		return "redirect:/admin/role/list";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String edit(Role role, Model model) {
		roleService.update(role);
		return "redirect:/admin/role/list";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable(value = "id") String id, Model model) {
		roleService.delete(id);
		return "redirect:/admin/role/list";
	}
}
