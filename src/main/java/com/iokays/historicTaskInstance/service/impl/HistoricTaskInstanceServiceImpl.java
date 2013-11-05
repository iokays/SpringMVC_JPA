package com.iokays.historicTaskInstance.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.activiti.engine.HistoryService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iokays.historicTaskInstance.service.HistoricTaskInstanceService;

@Service("historicTaskInstanceService")
@Transactional
public class HistoricTaskInstanceServiceImpl implements HistoricTaskInstanceService {
	
	/* (non-Javadoc)
	 * @see com.iokays.historicTaskInstance.service.HistoricTaskInstanceService#list(org.springframework.data.domain.Pageable)
	 */
	@Override
	public Page<HistoricTaskInstance> list(Pageable pageable) {
		final int first = (pageable.getPageNumber()) * pageable.getPageSize();
		final int end = first + pageable.getPageSize();

		final long total = historyService.createHistoricTaskInstanceQuery().count();
		List<HistoricTaskInstance> list = null;
		if (first < total) {
			list = historyService.createHistoricTaskInstanceQuery().orderByHistoricTaskInstanceStartTime().desc().listPage(first, end);
		} else {
			list = new ArrayList<HistoricTaskInstance>(0);
		}

		Page<HistoricTaskInstance> page = new PageImpl<HistoricTaskInstance>(list, pageable, total);
		return page;
	}
	
	@Resource
	HistoryService historyService;
}
