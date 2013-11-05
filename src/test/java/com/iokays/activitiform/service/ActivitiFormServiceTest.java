package com.iokays.activitiform.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.FormService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:applicationContext-security.xml"})
public class ActivitiFormServiceTest {
	
	@Test
	public void testCreate() {
		 Map<String, String> variables = new HashMap<String, String>();
		 variables.put("name", "test");
		 ProcessInstance processInstance = formService.submitStartFormData("My process", variables);
	}

	@Resource
	RuntimeService runtimeService;
	
	@Resource
	FormService formService;
	
	
	

}
