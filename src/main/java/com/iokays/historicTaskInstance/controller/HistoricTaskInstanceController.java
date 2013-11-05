package com.iokays.historicTaskInstance.controller;

import javax.annotation.Resource;

import org.activiti.engine.history.HistoricTaskInstance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.iokays.historicTaskInstance.service.HistoricTaskInstanceService;

@Controller
@RequestMapping(value = "/workflow/historicTaskInstance")
public class HistoricTaskInstanceController {
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(@PageableDefaults(pageNumber = 0, value = 50)Pageable pageable, Model model) {
		Page<HistoricTaskInstance> page = historicTaskInstanceService.list(pageable);
		model.addAttribute("page", page);
		return "workflow/historicTaskInstance/list";
	}
	
	@Resource
	HistoricTaskInstanceService historicTaskInstanceService;

}
