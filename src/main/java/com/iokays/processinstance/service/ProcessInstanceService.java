package com.iokays.processinstance.service;

import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.iokays.utils.workflow.ProcessInstanceStatus;

/**
 * 流程实例逻辑层接口
 * @author pengyuanbing@gmail.com
 *
 */
public interface ProcessInstanceService {
	/**
	 * 分页查询流程实例列表
	 * @param pageable
	 * @return
	 */
	public abstract Page<ProcessInstance> list(Pageable pageable);
	
	/**
	 * 分页查询流程实例列表
	 * @param pageable
	 * @param status
	 * @return
	 */
	public abstract Page<ProcessInstance> list(Pageable pageable, ProcessInstanceStatus status);
}
