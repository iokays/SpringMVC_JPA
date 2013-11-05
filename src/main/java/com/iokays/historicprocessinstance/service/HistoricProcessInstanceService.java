package com.iokays.historicprocessinstance.service;

import org.activiti.engine.history.HistoricProcessInstance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.iokays.utils.workflow.HistoricProcessInstanceStatus;

public interface HistoricProcessInstanceService {

	public abstract Page<HistoricProcessInstance> list(Pageable pageable);
	
	public abstract Page<HistoricProcessInstance> list(Pageable pageable, HistoricProcessInstanceStatus status);
	
	public abstract Page<HistoricProcessInstance> list(Pageable pageable, String userId);
	
	public abstract Page<HistoricProcessInstance> list(Pageable pageable, HistoricProcessInstanceStatus status, String userId);
	
}