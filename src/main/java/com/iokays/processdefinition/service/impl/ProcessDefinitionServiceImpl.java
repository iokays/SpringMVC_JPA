package com.iokays.processdefinition.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipInputStream;

import javax.annotation.Resource;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.iokays.processdefinition.service.ProcessDefinitionService;


@Service("processDefinitionService")
@Transactional
public class ProcessDefinitionServiceImpl implements ProcessDefinitionService {

	// 查询流程定义 分页列表
	public Page<Object[]> list(Pageable pageable) {
		final int first = (pageable.getPageNumber()) * pageable.getPageSize();
		final int end = first + pageable.getPageSize();
		final long total = repositoryService.createProcessDefinitionQuery().count();
		List<Object[]> list = new ArrayList<Object[]>(0);
		if (first < total) {
			List<ProcessDefinition> processDefinitions = repositoryService.createProcessDefinitionQuery().listPage(first, end);
			for (int i = 0; i < processDefinitions.size(); ++i) {
				ProcessDefinition processDefinition = processDefinitions.get(i);
				Deployment deployment = repositoryService.createDeploymentQuery().deploymentId(processDefinition.getDeploymentId()).singleResult();
				list.add(new Object[]{processDefinition, deployment});
			}
		}

		Page<Object[]> page = new PageImpl<Object[]>(list, pageable, total);
		return page;
	}

	// 部署流程定义
	public void deploy(MultipartFile file) {
		final String fileName = file.getOriginalFilename(); // 获取文件名称
		try {
			final InputStream fileInputStream = file.getInputStream(); // 获取文件流
			final String extension = FilenameUtils.getExtension(fileName); // 获取文件后缀名
//			Deployment deployment = null;
			if (extension.equals("zip") || extension.equals("bar")) {
				ZipInputStream zip = new ZipInputStream(fileInputStream);
				repositoryService.createDeployment().addZipInputStream(zip).deploy();
			} else {
				repositoryService.createDeployment().addInputStream(fileName, fileInputStream).deploy();
			}
		} catch (Exception e) {
			logger.error("error on deploy process, because of file input stream", e);
		}
	}
	
	//删除流程定义
	public void delete(final String deploymentId) {
		repositoryService.deleteDeployment(deploymentId);
	}
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private RepositoryService repositoryService;
}
