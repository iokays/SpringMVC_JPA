package com.iokays.taskinstance.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.activiti.engine.HistoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.task.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iokays.taskinstance.service.TaskInstanceService;

@Service("taskInstanceService")
@Transactional
public class TaskInstanceServiceImpl implements TaskInstanceService {
	/* (non-Javadoc)
	 * @see com.iokays.taskinstance.service.TaskInstanceService#list(org.springframework.data.domain.Pageable)
	 */
	@Override
	public  Page<Task> list(Pageable pageable) {
		final int first = (pageable.getPageNumber()) * pageable.getPageSize();
		final int end = first + pageable.getPageSize();

		final long total = taskService.createTaskQuery().active().count();
		List<Task> list = null;
		if (first < total) {
			list = taskService.createTaskQuery().active().listPage(first, end);
		} else {
			list = new ArrayList<Task>(0);
		}

		Page<Task> page = new PageImpl<Task>(list, pageable, total);
		return page;
	}
	
	/* (non-Javadoc)
	 * @see com.iokays.taskinstance.service.TaskInstanceService#historicList(org.springframework.data.domain.Pageable)
	 */
	@Override
	public Page<HistoricTaskInstance> historicList(Pageable pageable) {
		final int first = (pageable.getPageNumber()) * pageable.getPageSize();
		final int end = first + pageable.getPageSize();

		final long total = historyService.createHistoricTaskInstanceQuery().finished().count();
		List<HistoricTaskInstance> list = null;
		if (first < total) {
			list = historyService.createHistoricTaskInstanceQuery().finished().listPage(first, end);
		} else {
			list = new ArrayList<HistoricTaskInstance>(0);
		}

		Page<HistoricTaskInstance> page = new PageImpl<HistoricTaskInstance>(list, pageable, total);
		return page;
	}
	
	@Resource
	private TaskService taskService;
	@Resource
	private HistoryService historyService;
}
