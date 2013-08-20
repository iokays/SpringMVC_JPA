package com.iokays.resource.controller;


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

import com.iokays.resource.domain.Resource;
import com.iokays.resource.service.ResourceService;
import com.iokays.utils.domain.Level;
import com.iokays.utils.domain.Status;

@Controller
@RequestMapping("/admin/resource")
public class ResourceController {
	@javax.annotation.Resource
	private ResourceService resourceService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(@PageableDefaults(pageNumber = 0, value = 50, sort = "createDate = desc")
	@RequestParam(value = "pageable", required = false) Pageable pageable,
	@RequestParam(value = "status", required = false) Status status,
	@RequestParam(value = "level", required = false) Level level, Model model) {
		if (null == pageable) {
			pageable = new PageRequest(0, 50, Direction.DESC, "createDate");
		}
		Page<Resource> page = resourceService.findAll(pageable);
		model.addAttribute("page", page);
		return "/admin/resource/list";
	}
	
	@RequestMapping(value = "/load/{id}", method = RequestMethod.GET)
	public String load(@PathVariable(value = "id") String id, Model model) {
		Resource resource = resourceService.findOne(id);
		model.addAttribute("resource", resource);
		return "/admin/resource/edit";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(Resource resource, Model model) {
		resourceService.insert(resource);
		return "redirect:/admin/resource/list";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String edit(Resource resource, Model model) {
		resourceService.update(resource);
		return "redirect:/admin/resource/list";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable(value = "id") String id, Model model) {
		resourceService.delete(id);
		return "redirect:/admin/resource/list";
	}
}
