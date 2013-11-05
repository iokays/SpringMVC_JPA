package com.iokays.historicprocessinstance.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.activiti.engine.HistoryService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iokays.historicprocessinstance.service.HistoricProcessInstanceService;
import com.iokays.utils.workflow.HistoricProcessInstanceStatus;

@Service("historicProcessInstanceService")
@Transactional
public class HistoricProcessInstanceServiceImpl implements HistoricProcessInstanceService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iokays.historicprocessinstance.service.HistoricProcessInstanceService
	 * #historicList(org.springframework.data.domain.Pageable)
	 */
	@Override
	public Page<HistoricProcessInstance> list(Pageable pageable) {
		final int first = (pageable.getPageNumber()) * pageable.getPageSize();
		final int end = first + pageable.getPageSize();

		final long total = historyService.createHistoricProcessInstanceQuery().count();
		List<HistoricProcessInstance> list = null;
		if (first < total) {
			list = historyService.createHistoricProcessInstanceQuery().orderByProcessInstanceStartTime().desc().listPage(first, end);
		} else {
			list = new ArrayList<HistoricProcessInstance>(0);
		}

		Page<HistoricProcessInstance> page = new PageImpl<HistoricProcessInstance>(list, pageable, total);
		return page;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.iokays.historicprocessinstance.service.HistoricProcessInstanceService#list(org.springframework.data.domain.Pageable, java.lang.String)
	 */
	@Override
	public Page<HistoricProcessInstance> list(Pageable pageable, String userId) {
		if (null == userId) {
			return this.list(pageable);
		} else {
			final int first = (pageable.getPageNumber()) * pageable.getPageSize();
			final int end = first + pageable.getPageSize();

			final long total = historyService.createHistoricProcessInstanceQuery().involvedUser(userId).count();
			List<HistoricProcessInstance> list = null;
			if (first < total) {
				list = historyService.createHistoricProcessInstanceQuery().involvedUser(userId).orderByProcessInstanceStartTime().desc().listPage(first, end);
			} else {
				list = new ArrayList<HistoricProcessInstance>(0);
			}

			Page<HistoricProcessInstance> page = new PageImpl<HistoricProcessInstance>(list, pageable, total);
			return page;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iokays.historicprocessinstance.service.HistoricProcessInstanceService
	 * #list(org.springframework.data.domain.Pageable,
	 * com.iokays.utils.workflow.ProcessInstanceStatus)
	 */
	@Override
	public Page<HistoricProcessInstance> list(Pageable pageable, HistoricProcessInstanceStatus status) {
		if (null == status) {
			return this.list(pageable);
		} else {

			final int first = (pageable.getPageNumber()) * pageable.getPageSize();
			final int end = first + pageable.getPageSize();

			List<HistoricProcessInstance> list = null;
			long total = 0;
			switch (status) {
			case finished :
				total = historyService.createHistoricProcessInstanceQuery().finished().count();
				if (first < total) {
					list = historyService.createHistoricProcessInstanceQuery().finished().orderByProcessInstanceStartTime().desc().listPage(first, end);
				} else {
					list = new ArrayList<HistoricProcessInstance>(0);
				}
				break;

			case unfinished :
				total = historyService.createHistoricProcessInstanceQuery().unfinished().count();
				if (first < total) {
					list = historyService.createHistoricProcessInstanceQuery().unfinished().orderByProcessInstanceStartTime().desc().listPage(first, end);
				} else {
					list = new ArrayList<HistoricProcessInstance>(0);
				}
				break;
			default :
				throw new IllegalArgumentException("Unsupported attributes: " + status.toString());
			}

			Page<HistoricProcessInstance> page = new PageImpl<HistoricProcessInstance>(list, pageable, total);
			return page;
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.iokays.historicprocessinstance.service.HistoricProcessInstanceService#list(org.springframework.data.domain.Pageable, com.iokays.utils.workflow.HistoricProcessInstanceStatus, java.lang.String)
	 */
	@Override
	public Page<HistoricProcessInstance> list(Pageable pageable, HistoricProcessInstanceStatus status, String userId) {
		if (null == userId) {
			return this.list(pageable, status);
		} else if (null == status) {
			return this.list(pageable, userId);
		} else {
			final int first = (pageable.getPageNumber()) * pageable.getPageSize();
			final int end = first + pageable.getPageSize();

			List<HistoricProcessInstance> list = null;
			long total = 0;
			switch (status) {
			case finished :
				total = historyService.createHistoricProcessInstanceQuery().involvedUser(userId).finished().count();
				if (first < total) {
					list = historyService.createHistoricProcessInstanceQuery().involvedUser(userId).finished().orderByProcessInstanceStartTime().desc().listPage(first, end);
				} else {
					list = new ArrayList<HistoricProcessInstance>(0);
				}
				break;

			case unfinished :
				total = historyService.createHistoricProcessInstanceQuery().involvedUser(userId).unfinished().count();
				if (first < total) {
					list = historyService.createHistoricProcessInstanceQuery().involvedUser(userId).unfinished().orderByProcessInstanceStartTime().desc().listPage(first, end);
				} else {
					list = new ArrayList<HistoricProcessInstance>(0);
				}
				break;
			default :
				throw new IllegalArgumentException("Unsupported attributes: " + status.toString());
			}

			Page<HistoricProcessInstance> page = new PageImpl<HistoricProcessInstance>(list, pageable, total);
			return page;
		}

	}

	@Resource
	private HistoryService historyService;
}
