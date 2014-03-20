package com.iokays.column.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
import com.iokays.column.domain.Column.Grade;
import com.iokays.column.service.ColumnService;
import com.iokays.template.service.TemplateService;
import com.iokays.utils.fileupload.FileUpload;

@Controller
public class ColumnController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ColumnController.class);
	
    @RequestMapping(value = "/columns", method = RequestMethod.GET)
    public ModelAndView listAll() {
        ModelAndView mav = new ModelAndView("columns");
        List<Column> columns = columnService.findAllByGrade(Column.Grade.one, new Sort("sort"));
        for (int i = 0, length = columns.size(); i != length; ++i) {
            Column column = columns.get(i);
            List<Column> children = columnService.findAllByParent(column, new Sort("sort"));
            column.setChildren(children);
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
        new File(_columnDir + File.separator + id + ".jpg").delete();
    }

    @RequestMapping(value = "/columns/new", method = RequestMethod.GET)
    public ModelAndView toInsert() {
        ModelAndView mav = new ModelAndView("column");
        List<Column> columns = columnService.findAllByGrade(Column.Grade.one, new Sort("sort"));
        mav.addObject("columns", columns);
        return mav;
    }

    @RequestMapping(value = "/columns", method = RequestMethod.POST)
    @ResponseBody
    public String insert(Column column, Long timeInMillis, HttpServletRequest request) {
    	column = columnService.save(column);
    	 
    	final String _timeInMillis = timeInMillis.toString();
    	LOGGER.debug("timeInMillis:{}", _timeInMillis);
    	final String _tempColumnFileName = (String)request.getSession().getAttribute(_timeInMillis);	//获取已上传文件名
    	LOGGER.debug("Session:Start:_tempColumnFileName:{}", (String)request.getSession().getAttribute(_timeInMillis));
    	if (null != _tempColumnFileName) {
    		try {
    			FileUpload.copyFileNIO(new File(_columnTempDir + File.separator + _tempColumnFileName), new File(_columnDir + File.separator + column.getId() + ".jpg"));
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
       
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
        LOGGER.debug("timeInMillis:{}", _timeInMillis);
        final String _tempColumnFileName = (String)request.getSession().getAttribute(_timeInMillis);	//获取已上传文件名
    	LOGGER.debug("Session:Start:_tempColumnFileName:{}", (String)request.getSession().getAttribute(_timeInMillis));
        
    	Column column = columnService.update(id, map);
    	
    	if (null != _tempColumnFileName) {
    		try {
				FileUpload.copyFileNIO(new File(_columnTempDir + File.separator + _tempColumnFileName), new File(_columnDir + File.separator + column.getId() + ".jpg"));
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
        
        request.getSession().setAttribute(_timeInMillis, null);		//清空文件上传的临时路径
        LOGGER.debug("Session:End:_tempHomePath:{}", (String)request.getSession().getAttribute(map.get("timeInMillis").toString()));
       
    }
    
    @RequestMapping(value = "/columns/fileupload", method = RequestMethod.POST)
    @ResponseBody
    public void fileupload(@RequestParam(value = "file", required = true) MultipartFile file, Long timeInMillis, HttpServletRequest request)  {
    	LOGGER.debug("url:{}, fileName:{}, fileUrl:", "/columns/fileupload", file.getName(), file.getOriginalFilename());
    	LOGGER.debug("timeInMillis:{}", timeInMillis.toString());
    	String _tempName = FileUpload.uploadImages(file, _columnTempDir);
    	request.getSession().setAttribute(timeInMillis.toString(), _tempName);		//路径保存到Session
    }
    
    @RequestMapping(value = "/columns/generateStaticPage")
    @ResponseBody
    public void generateStaticPage() {
    	List<Column> columns = columnService.findAll();try {
    		for (Column column : columns) {
    			if (column.getGrade() == Grade.one) {
    				templateService.buildOneColumn(column.getId());
    			} else if (column.getGrade() == Grade.two){
    				templateService.buildTwoColumn(column.getId());
    			}
    		}
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @Resource
    private ColumnService columnService;
    
    @Resource
    private TemplateService templateService;
    
    @Value("#{properties.getProperty('_columnDir')}")
    private String _columnDir;
    
    @Value("#{properties.getProperty('_columnTempDir')}")
    private String _columnTempDir;
    
    
	public String get_columnTempDir() {
		return _columnTempDir;
	}

	public void set_columnTempDir(String _columnTempDir) {
		this._columnTempDir = _columnTempDir;
	}

	public String get_columnDir() {
		return _columnDir;
	}
	public void set_columnDir(String _columnDir) {
		this._columnDir = _columnDir;
	}

}
