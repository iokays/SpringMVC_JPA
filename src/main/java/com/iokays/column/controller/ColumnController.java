package com.iokays.column.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iokays.column.domain.Column;
import com.iokays.column.service.ColumnService;

@Controller
public class ColumnController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ColumnController.class);
	
    @RequestMapping(value = "/columns", method = RequestMethod.GET)
    public ModelAndView listAll() {
        ModelAndView mav = new ModelAndView("columns");
        List<Object[]> columns = new ArrayList<Object[]>();
        List<Column> list = columnService.findAllByGrade(Column.Grade.one, new Sort("sort"));
        for (int i = 0, length = list.size(); i != length; ++i) {
            Column column = list.get(i);
            List<Column> children = columnService.findAllByParent(column, new Sort("sort"));
            columns.add(new Object[]{column, children});
        }
        mav.addObject("columns", columns);
        return mav;
    }

    @RequestMapping(value = "/columns/{id}", method = RequestMethod.GET)
    public ModelAndView findOne(@PathVariable("id") String id) {
        ModelAndView mav = new ModelAndView("column");
        List<Column> columns = columnService.findAllByGrade(Column.Grade.one, new Sort("sort"));
        Column column = columnService.findOne(id);
        mav.addObject("columns", columns);
        mav.addObject("column", column);
        return mav;
    }

    @RequestMapping(value = "/columns/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable("id") String id) {
        columnService.delete(id);
    }

    @RequestMapping(value = "/column", method = RequestMethod.GET)
    public ModelAndView toInsert() {
        ModelAndView mav = new ModelAndView("column");
        List<Column> columns = columnService.findAllByGrade(Column.Grade.one, new Sort("sort"));
        mav.addObject("columns", columns);
        return mav;
    }

    @RequestMapping(value = "/columns", method = RequestMethod.POST)
    @ResponseBody
    public String insert(Column column, HttpServletRequest request) {
    	final String _tempColumnFileName = (String)request.getSession().getAttribute("_tempColumnFileName");	//获取已上传文件名
    	LOGGER.debug("Session:Start:_tempColumnFileName:{}", (String)request.getSession().getAttribute("_tempColumnFileName"));
    	if (null != _tempColumnFileName) {
    		column.setImageUrl(_tempColumnFileName);
    		LOGGER.debug("column.imageUrl:{}", column.getImageUrl());
    		new File(_columnPathTemp + File.separator + _tempColumnFileName).renameTo(new File(_columnPath + File.separator + column.getImageUrl()));		//将文件移到到确定目录
    	}
        columnService.save(column);
        request.getSession().setAttribute("_tempArticleFileName", null);		//清空文件上传的临时路径
        LOGGER.debug("Session:End:_tempHomePath:{}", (String)request.getSession().getAttribute("_tempArticleFileName"));
        
        return column.getId();
    }

    @RequestMapping(value = "/columns/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public void update(@PathVariable("id") String id, @RequestBody String body, HttpServletRequest request) throws IOException {
    	final String _tempColumnFileName = (String)request.getSession().getAttribute("_tempColumnFileName");	//获取已上传文件名
    	LOGGER.debug("Session:Start:_tempColumnFileName:{}", (String)request.getSession().getAttribute("_tempColumnFileName"));
    	
        ObjectMapper objectMapper = new ObjectMapper();
        @SuppressWarnings("unchecked")
        Map<String, String> map = objectMapper.readValue(body, Map.class);
        
        if (null != _tempColumnFileName) {
        	map.put("imageUrl", _tempColumnFileName);
    		LOGGER.debug("homePage.imageUrl:{}", map.get("imageUrl"));
    		new File(_columnPathTemp + File.separator + _tempColumnFileName).renameTo(new File(_columnPath + File.separator + map.get("imageUrl")));		//将文件移到到确定目录
    	}
        
        columnService.update(id, map);
    }
    
    @RequestMapping(value = "/columns/fileupload", method = RequestMethod.POST)
    @ResponseBody
    public void fileupload(@RequestParam(value = "file", required = true) MultipartFile file, HttpServletRequest request) throws IllegalStateException, IOException {
    	LOGGER.debug("url:{}, fileName:{}, fileUrl:", "/columns/fileupload", file.getName(), file.getOriginalFilename());
    	File _tempDir = new File(_columnPathTemp);
    	if (!_tempDir.exists()) {			//不存在，新建之					
    		_tempDir.mkdirs();
    	}
    	
    	String fileName = file.getOriginalFilename();// 获取文件名称
        String extension = FilenameUtils.getExtension(fileName);	//获取文件后缀
        
        final String _tempName = UUID.randomUUID().toString() + "." + extension;	//临时文件名(UUID)
        final String _tempPath = _columnPathTemp + File.separator + _tempName;		
        
        File _target = new File(_tempPath);
        
        file.transferTo(_target);				//移动文件到临时文件.
        
        LOGGER.debug("return:{}", _tempPath);
        request.getSession().setAttribute("_tempColumnFileName", _tempName);		//路径保存到Session
    }

    @Resource
    private ColumnService columnService;
    
    @Value("#{properties.getProperty('_columnPath')}")
    private String _columnPath;
    
    @Value("#{properties.getProperty('_columnPathTemp')}")
    private String _columnPathTemp;

	public String get_columnPathTemp() {
		return _columnPathTemp;
	}
	public void set_columnPathTemp(String _columnPathTemp) {
		this._columnPathTemp = _columnPathTemp;
	}

	public String get_columnPath() {
		return _columnPath;
	}
	public void set_columnPath(String _columnPath) {
		this._columnPath = _columnPath;
	}

}
