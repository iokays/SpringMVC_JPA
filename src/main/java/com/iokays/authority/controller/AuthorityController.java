package com.iokays.authority.controller;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.iokays.authority.domain.Authority;
import com.iokays.authority.service.AuthorityService;
import com.iokays.utils.domain.Level;
import com.iokays.utils.domain.Status;

@Controller
@RequestMapping("/admin/authority")
public class AuthorityController {
	@Resource
	private AuthorityService authorityService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(@PageableDefaults(pageNumber = 0, value = 50, sort = "createDate = desc")
					@RequestParam(value = "pageable", required = false) Pageable pageable,
					@RequestParam(value = "status", required = false) Status status,
					@RequestParam(value = "level", required = false) Level level, Model model) {
		if (null == pageable) {
			pageable = new PageRequest(0, 50, Direction.DESC, "createDate");
		}
		Page<Authority> page = authorityService.findAll(pageable);
		model.addAttribute("page", page);
		return "/admin/authority/list";
	}
	
	@RequestMapping(value = "/load/{id}", method = RequestMethod.GET)
	public String load(@PathVariable(value = "id") String id, Model model) {
		Authority authority = authorityService.findOne(id);
		model.addAttribute("authority", authority);
		return "/admin/authority/edit";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(Authority authority, Model model) {
		authorityService.insert(authority);
		return "redirect:/admin/authority/list";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String edit(Authority authority, Model model) {
		authorityService.update(authority);
		return "redirect:/admin/authority/list";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable(value = "id") String id, Model model) {
		authorityService.delete(id);
		return "redirect:/admin/authority/list";
	}
}
