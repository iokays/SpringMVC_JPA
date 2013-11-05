package com.iokays.processinstance.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iokays.processinstance.service.ProcessInstanceService;
import com.iokays.utils.workflow.ProcessInstanceStatus;

@Service("processInstanceService")
@Transactional
public class ProcessInstanceServiceImpl implements ProcessInstanceService{
	
	/*
	 * (non-Javadoc)
	 * @see com.iokays.processinstance.service.ProcessInstanceService#list(org.springframework.data.domain.Pageable)
	 */
	@Override
	public Page<ProcessInstance> list(Pageable pageable) {
		final int first = (pageable.getPageNumber()) * pageable.getPageSize();
		final int end = first + pageable.getPageSize();

		final long total = runtimeService.createProcessInstanceQuery().count();
		List<ProcessInstance> list = null;
		if (first < total) {
			list = runtimeService.createProcessInstanceQuery().listPage(first, end);
		} else {
			list = new ArrayList<ProcessInstance>(0);
		}

		Page<ProcessInstance> page = new PageImpl<ProcessInstance>(list, pageable, total);
		return page;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.iokays.processinstance.service.ProcessInstanceService#list(org.springframework.data.domain.Pageable, com.iokays.utils.workflow.ProcessInstanceStatus)
	 */
	@Override
	public Page<ProcessInstance> list(Pageable pageable, ProcessInstanceStatus status) {
		if (null == status) {
			return this.list(pageable);
		} else {
			final int first = (pageable.getPageNumber()) * pageable.getPageSize();
			final int end = first + pageable.getPageSize();
			List<ProcessInstance> list = null;
			long total = 0;
			
			switch (status) {
			case active :
				total = runtimeService.createProcessInstanceQuery().active().count();
				if (first < total) {
					list = runtimeService.createProcessInstanceQuery().active().listPage(first, end);
				} else {
					list = new ArrayList<ProcessInstance>(0);
				}
				break;
			case suspended :
				total = runtimeService.createProcessInstanceQuery().suspended().count();
				if (first < total) {
					list = runtimeService.createProcessInstanceQuery().suspended().listPage(first, end);
				} else {
					list = new ArrayList<ProcessInstance>(0);
				}
				break;
			default :
				 throw new IllegalArgumentException("Unsupported attributes: " + status.toString());
			}
		
			
			Page<ProcessInstance> page = new PageImpl<ProcessInstance>(list, pageable, total);
			return page;
		}
	}
	
	@Resource
	private RuntimeService runtimeService;
	
}
