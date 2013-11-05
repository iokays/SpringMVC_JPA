package com.iokays.processinstance.controller;

import javax.annotation.Resource;

import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.iokays.processinstance.service.ProcessInstanceService;

@Controller
@RequestMapping(value = "/workflow/processInstance")
public class ProcessInstanceController {
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(@PageableDefaults(pageNumber = 0, value = 50)Pageable pageable, Model model) {
		Page<ProcessInstance> page = processInstanceService.list(pageable);
		model.addAttribute("page", page);
		return "workflow/processInstance/list";
	}
	
	@Resource
	ProcessInstanceService processInstanceService;
}

