package com.iokays.taskinstance.service;

import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.task.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaskInstanceService {

	public abstract Page<Task> list(Pageable pageable);

	public abstract Page<HistoricTaskInstance> historicList(Pageable pageable);

}