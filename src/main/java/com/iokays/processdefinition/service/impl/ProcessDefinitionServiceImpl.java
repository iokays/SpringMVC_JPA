package com.iokays.processdefinition.service.impl;

import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

import javax.annotation.Resource;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.io.FilenameUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service(value = "processDefinitionService")
@Transactional
public class ProcessDefinitionServiceImpl {

	// 查询流程定义 分页列表
	public Page<ProcessDefinition> page(Pageable pageable) {
		final int first = (pageable.getPageNumber() - 1) * pageable.getPageSize();
		final int end = first + pageable.getPageSize();

		final List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().listPage(first, end);
		final long total = repositoryService.createProcessDefinitionQuery().count();

		Page<ProcessDefinition> page = new PageImpl<ProcessDefinition>(list, pageable, total);
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
			e.printStackTrace();
		}
	}

	@Resource
	private RepositoryService repositoryService;
}
