package com.iokays.activitiform.listener;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.delegate.Expression;

import com.iokays.activitiform.domain.ActivitiForm;

public class ActivitiFormListener implements ExecutionListener {

	private static final long serialVersionUID = 4176125947141711768L;
	
	private Expression activitiForm;

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		execution.setVariable("activitiForm", (ActivitiForm)activitiForm.getValue(execution));
	}
	
}
