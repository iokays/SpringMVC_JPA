package com.iokays.column.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import com.iokays.utils.fileupload.FileUpload;

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
    public String insert(Column column, Long timeInMillis, HttpServletRequest request) {
    	final String _timeInMillis =timeInMillis.toString();
    	final String _tempColumnFileName = (String)request.getSession().getAttribute(_timeInMillis);	//获取已上传文件名
    	String extension = FilenameUtils.getExtension(_tempColumnFileName);	//获取文件后缀
    	LOGGER.debug("Session:Start:_tempColumnFileName:{}", (String)request.getSession().getAttribute(_timeInMillis));
    	if (null != _tempColumnFileName) {
    		column.setImageUrl(column.getMarking() + "." + extension);
    		LOGGER.debug("column.imageUrl:{}", column.getImageUrl());
    		new File(_columnDirTemp + File.separator + _tempColumnFileName).renameTo(new File(_columnDir + File.separator + column.getImageUrl()));		//将文件移到到确定目录
    	}
        columnService.save(column);
        request.getSession().setAttribute(_timeInMillis, null);		//清空文件上传的临时路径
        LOGGER.debug("Session:End:_tempHomePath:{}", (String)request.getSession().getAttribute(_timeInMillis));
        
        return column.getId();
    }

    @RequestMapping(value = "/columns/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public void update(@PathVariable("id") String id, @RequestBody String body, HttpServletRequest request) throws IOException {
    	
        ObjectMapper objectMapper = new ObjectMapper();
      
        @SuppressWarnings("unchecked")
		Map<String, Object> map = objectMapper.readValue(body, Map.class);
        final String _timeInMillis = map.get("timeInMillis").toString();
        map.remove(_timeInMillis);
        
        final String _tempColumnFileName = (String)request.getSession().getAttribute(_timeInMillis);	//获取已上传文件名
    	String extension = FilenameUtils.getExtension(_tempColumnFileName);	//获取文件后缀
    	LOGGER.debug("Session:Start:_tempColumnFileName:{}", (String)request.getSession().getAttribute(_timeInMillis));
        
        if (null != _tempColumnFileName) {
        	map.put("imageUrl", map.get("marking") + "." + extension);
    		LOGGER.debug("homePage.imageUrl:{}", map.get("imageUrl"));
    		new File(_columnDirTemp + File.separator + _tempColumnFileName).renameTo(new File(_columnDir + File.separator + map.get("imageUrl")));		//将文件移到到确定目录
    	}
        
        request.getSession().setAttribute(_timeInMillis, null);		//清空文件上传的临时路径
        LOGGER.debug("Session:End:_tempHomePath:{}", (String)request.getSession().getAttribute(map.get("timeInMillis").toString()));
        
        columnService.update(id, map);
    }
    
    @RequestMapping(value = "/columns/fileupload", method = RequestMethod.POST)
    @ResponseBody
    public void fileupload(@RequestParam(value = "file", required = true) MultipartFile file, Long timeInMillis, HttpServletRequest request) throws IllegalStateException, IOException {
    	String _tempName = FileUpload.uploadImages(file, _columnDirTemp);
    	request.getSession().setAttribute(timeInMillis.toString(), _tempName);		//路径保存到Session
    }

    @Resource
    private ColumnService columnService;
    
    @Value("#{properties.getProperty('_columnDir')}")
    private String _columnDir;
    
    @Value("#{properties.getProperty('_columnDirTemp')}")
    private String _columnDirTemp;

	public String get_columnDirTemp() {
		return _columnDirTemp;
	}
	public void set_columnDirTemp(String _columnDirTemp) {
		this._columnDirTemp = _columnDirTemp;
	}

	public String get_columnDir() {
		return _columnDir;
	}
	public void set_columnDir(String _columnDir) {
		this._columnDir = _columnDir;
	}

}
