package com.iokays.activitiform.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iokays.activitiform.domain.ActivitiForm;
import com.iokays.activitiform.repository.ActivitiFormRepository;
import com.iokays.activitiform.service.ActivitiFormService;

@Service("activitiFormService")
@Transactional
public class ActivitiFormServiceImpl implements ActivitiFormService {
	/* (non-Javadoc)
	 * @see com.iokays.activitiform.service.ActivitiFormService#save(java.lang.String)
	 */
	@Override
	public ActivitiForm create() {
		return activitiFormRepository.save(new ActivitiForm());
	}
	
	@Resource
	ActivitiFormRepository activitiFormRepository;
}
