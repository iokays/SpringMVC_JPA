package com.iokays.processdefinition.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface ProcessDefinitionService {
	public abstract Page<Object[]> list(Pageable pageable);
	public abstract void deploy(MultipartFile file);
	public abstract void delete(final String deploymentId);
}
