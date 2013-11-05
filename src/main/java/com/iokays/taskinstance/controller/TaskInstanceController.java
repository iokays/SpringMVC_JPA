package com.iokays.taskinstance.controller;

import javax.annotation.Resource;

import org.activiti.engine.task.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.iokays.taskinstance.service.TaskInstanceService;

@Controller
@RequestMapping(value = "/workflow/taskInstance")
public class TaskInstanceController {
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(@PageableDefaults(pageNumber = 0, value = 50, sort = "createDate", sortDir=Direction.DESC)Pageable pageable, Model model) {
		Page<Task> page = taskInstanceService.list(pageable);
		model.addAttribute("page", page);
		return "workflow/processDefinition/list";
	}
	
	@Resource
	private TaskInstanceService taskInstanceService;
}
