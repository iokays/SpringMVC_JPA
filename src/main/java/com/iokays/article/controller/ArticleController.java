package com.iokays.article.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iokays.article.domain.Article;
import com.iokays.article.service.ArticleService;
import com.iokays.column.domain.Column;
import com.iokays.column.service.ColumnService;
import com.iokays.template.service.TemplateService;
import com.iokays.utils.fileupload.FileUpload;

/**
 * 文章管理，控制层实现类
 */
@Controller
public class ArticleController {

    private final static Logger LOGGER = LoggerFactory.getLogger(ArticleController.class);

    /**
     * @param columnId 栏目Id
     * @param pageable 分页参数
     * @return
     */
    @RequestMapping(value = "/articles", method = RequestMethod.GET)
    public ModelAndView listAll(
            @RequestParam(value = "columnId", required = false) String columnId,
            @PageableDefault(page = 0, value = 50, sort = {"createDate"}, direction = Direction.DESC)
            @RequestParam(value = "pageable", required = false) Pageable pageable) {

        ModelAndView mav = new ModelAndView("articles");
        Page<Object[]> page = (StringUtils.isNotBlank(columnId)) ? articleService.findTitleAndColumnNameByColumnId(columnId, pageable) : articleService.findTitleAndColumnName(pageable);
        mav.addObject("page", page);
        mav.addObject("columnId", columnId);
        return mav;
    }

    @RequestMapping(value = "/articles/{id}", method = RequestMethod.GET)
    public ModelAndView findOne(@PathVariable("id") String id) {
    	LOGGER.debug("id:{}", id);
        ModelAndView mav = new ModelAndView("article");
        
        mav.addObject("article", articleService.findOne(id));
        mav.addObject("columns", columnService.findAllByGrade(Column.Grade.two));
        return mav;
    }

    @RequestMapping(value = "/article", method = RequestMethod.GET)
    public ModelAndView toInsert(@RequestParam(value = "columnId", required = false) String columnId) {
        ModelAndView mav = new ModelAndView("article");
        mav.addObject("columnId", columnId);
        mav.addObject("columns", columnService.findAllByGrade(Column.Grade.two));
        return mav;
    }

    @RequestMapping(value = "/articles/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public String update(@PathVariable("id") String id, @RequestBody String body, HttpServletRequest request) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        @SuppressWarnings("unchecked")
        Map<String, Object> map = objectMapper.readValue(body, Map.class);
        final String _timeInMillis = map.get("timeInMillis").toString();
        map.remove(_timeInMillis);
        
        final String _tempArticleFileName = (String)request.getSession().getAttribute(_timeInMillis);	//获取已上传文件名
    	String extension = FilenameUtils.getExtension(_tempArticleFileName);	//获取文件后缀
    	
        if (null != _tempArticleFileName) {
        	map.put("imageUrl", map.get("title") + "." + extension);
    		LOGGER.debug("homePage.imageUrl:{}", map.get("imageUrl"));
    		new File(_articleDirTemp + File.separator + _tempArticleFileName).renameTo(new File(_articleDir + File.separator + map.get("imageUrl")));		//将文件移到到确定目录
    	
    	}
        
        request.getSession().setAttribute(_timeInMillis, null);		//清空文件上传的临时路径
        final String resutl = articleService.update(id, map).toString();
        
        try {
    		templateService.build(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
       
        return resutl;
    }

    @RequestMapping(value = "/articles", method = RequestMethod.POST)
    @ResponseBody
    public String insert(Article article, Long timeInMillis, HttpServletRequest request) {
    	final String _timeInMillis = timeInMillis.toString(); 
    	final String _tempArticleFileName = (String)request.getSession().getAttribute(_timeInMillis);	//获取已上传文件名
    	String extension = FilenameUtils.getExtension(_tempArticleFileName);	//获取文件后缀
    	
    	if (null != _tempArticleFileName) {
    		article.setImageUrl(article.getTitle() + "." + extension);
    		LOGGER.debug("column.imageUrl:{}", article.getImageUrl());
    		new File(_articleDirTemp + File.separator + _tempArticleFileName).renameTo(new File(_articleDir + File.separator + article.getImageUrl()));		//将文件移到到确定目录
    	}
    	
    	request.getSession().setAttribute(_timeInMillis, null);		//清空文件上传的临时路径
    	articleService.save(article);
    	
    	try {
    		templateService.build(article.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
        return article.getId();
    }

    @RequestMapping(value = "/articles/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable("id") String id) {
        articleService.delete(id);
    }
    
    @RequestMapping(value = "/articles/fileupload", method = RequestMethod.POST)
    @ResponseBody
    public void fileupload(@RequestParam(value = "file", required = true) MultipartFile file, Long timeInMillis, HttpServletRequest request) throws IllegalStateException, IOException {
    	LOGGER.debug("url:{}, fileName:{}, fileUrl:", "/articles/fileupload", file.getName(), file.getOriginalFilename());
    	String _tempName = FileUpload.uploadImages(file,  _articleDirTemp);
    	request.getSession().setAttribute(timeInMillis.toString(), _tempName);		//路径保存到Session
    }
    
    @Value("#{properties.getProperty('_articleDir')}")
    private String _articleDir;			//保存路径
    
    @Value("#{properties.getProperty('_articleDirTemp')}")
    private String _articleDirTemp;		//临时保存路径
    
    public String get_articleDir() {
		return _articleDir;
	}

	public void set_articleDir(String _articleDir) {
		this._articleDir = _articleDir;
	}

	public String get_articleDirTemp() {
		return _articleDirTemp;
	}

	public void set_articleDirTemp(String _articleDirTemp) {
		this._articleDirTemp = _articleDirTemp;
	}

	@Resource
    ArticleService articleService;

    @Resource
    ColumnService columnService;
    
    @Resource
    TemplateService templateService;

}
