package com.iokays.historicprocessinstance.controller;

import javax.annotation.Resource;

import org.activiti.engine.history.HistoricProcessInstance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.iokays.historicprocessinstance.service.HistoricProcessInstanceService;

@Controller
@RequestMapping(value = "/workflow/historicProcessInstance")
public class HistoricProcessInstanceController {
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(@PageableDefaults(pageNumber = 0, value = 50)Pageable pageable, Model model) {
		Page<HistoricProcessInstance> page = historicProcessInstanceService.list(pageable);
		model.addAttribute("page", page);
		return "workflow/historicProcessInstance/list";
	}
	
	@Resource
	HistoricProcessInstanceService historicProcessInstanceService;
}
