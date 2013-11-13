package com.iokays.processdefinition.controller;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.iokays.processdefinition.service.ProcessDefinitionService;

@Controller
@RequestMapping("/workflow/processDefinition")
public class ProcessDefinitionController {
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(@PageableDefaults(pageNumber = 0, value = 50)Pageable pageable, Model model) {
		Page<Object[]> page = processDefinitionService.list(pageable);
		model.addAttribute("page", page);
		return "workflow/processDefinition/list";
	}
	
	@RequestMapping(value = "/deploy", method = RequestMethod.POST)
	public String deploy(@RequestParam(value = "file", required = false) MultipartFile file) {
		processDefinitionService.deploy(file);
		return "redirect:/workflow/processDefinition/list";
	}
	
	@RequestMapping(value = "/delete/{deploymentId}", method = RequestMethod.GET)
	public String delete(@PathVariable(value = "deploymentId") final String deploymentId) {
		processDefinitionService.delete(deploymentId);
		return "redirect:/workflow/processDefinition/list";
	}
	
	@Resource
	ProcessDefinitionService processDefinitionService;

}
