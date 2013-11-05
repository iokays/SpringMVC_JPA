package com.iokays.historicTaskInstance.service;

import org.activiti.engine.history.HistoricTaskInstance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HistoricTaskInstanceService {

	public abstract Page<HistoricTaskInstance> list(Pageable pageable);

}