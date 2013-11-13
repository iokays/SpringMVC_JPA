package com.iokays.activitiform.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.FormService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:applicationContext-security.xml"})
public class ActivitiFormServiceTest {
	
	@Test
	public void testCreate() {
		 String processDefinitionId = repositoryService.createProcessDefinitionQuery()
			      .processDefinitionKey("myProcess")
			      .latestVersion()
			      .singleResult()
			      .getId();
		 
		 Map<String, String> formVariables = new HashMap<String, String>();
		 formVariables.put("test", "201311061751");
		 ProcessInstance processInstance = formService.submitStartFormData(processDefinitionId, formVariables);
		 
		 Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
		 Map<String, String> variables = new HashMap<String, String>();
		 variables.put("name", "pengyuanbing3");
		 formService.submitTaskFormData(task.getId(), variables);
		 
//		 Map<String, Object> variables = new HashMap<String, Object>();
//		/* ActivitiForm activitiForm = activitiFormService.create();
//		 variables.put("activitiForm", activitiForm);*/
//		 ProcessInstance processInstance =  runtimeService.startProcessInstanceById(processDefinitionId, variables);
//		 
//		 ActivitiForm val = (ActivitiForm)runtimeService.getVariable(processInstance.getId(), "activitiForm");
//		
		 
	}

	@Resource
	RepositoryService repositoryService;
	
	@Resource
	RuntimeService runtimeService;
	
	@Resource
	FormService formService;
	
	@Resource
	TaskService taskService;
	
	@Resource
	ActivitiFormService activitiFormService;

}
