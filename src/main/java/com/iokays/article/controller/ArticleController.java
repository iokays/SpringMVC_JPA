package com.iokays.article.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
            Pageable pageable) {
    	LOGGER.debug("pageable:{}", pageable.toString());
    	
        ModelAndView mav = new ModelAndView("articles");
        Page<Map<String, Object>> page = (StringUtils.isNotBlank(columnId)) ? articleService.findMapByColumn(columnId, pageable) : articleService.findMap(pageable);
        mav.addObject("page", page);
        mav.addObject("columnId", columnId);
        return mav;
    }

    @RequestMapping(value = "/articles/{id}", method = RequestMethod.GET)
    public ModelAndView findOne(@PathVariable("id") String id) {
    	LOGGER.debug("id:{}", id);
        ModelAndView mav = new ModelAndView("article");
        Article article = articleService.findOne(id);
        mav.addObject("article", article);
        mav.addObject("content", new String(article.getContent()));
        mav.addObject("columns", columnService.findAllByGrade(Column.Grade.two));
        return mav;
    }

    @RequestMapping(value = "/articles/new", method = RequestMethod.GET)
    public ModelAndView toInsert(@RequestParam(value = "columnId", required = false) String columnId) {
        ModelAndView mav = new ModelAndView("article");
        mav.addObject("columnId", columnId);
        mav.addObject("columns", columnService.findAllByGrade(Column.Grade.two));
        return mav;
    }

    @RequestMapping(value = "/articles/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public void update(@PathVariable("id") String id, @RequestBody String body, HttpServletRequest request) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        @SuppressWarnings("unchecked")
        Map<String, Object> map = objectMapper.readValue(body, Map.class);
        final String _timeInMillis = map.get("timeInMillis").toString();
        LOGGER.debug("timeInMillis:{}", _timeInMillis);
        map.remove(_timeInMillis);
        
        Article article = articleService.update(id, map);
        
        final String _tempArticleFileName = (String)request.getSession().getAttribute(_timeInMillis);	//获取已上传文件名
    	
        if (null != _tempArticleFileName) {
    		try {
				FileUpload.copyFileNIO(new File(_articleTempDir + File.separator + _tempArticleFileName), new File(_articleDir + File.separator + article.getId() + ".jpg"));
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
        
        request.getSession().setAttribute(_timeInMillis, null);		//清空文件上传的临时路径
        
        final Article _article = article;
        new Thread(new Runnable() {									//生成静态页，失败，不影响数据的准确性
			public void run() {
				try {
					templateService.buildArticle(_article.getId());
					Column column = columnService.findOne(_article.getColumn().getId());
					templateService.buildTwoColumn(column.getId());
					templateService.buildOneColumn(column.getParent().getId());
					templateService.buildHomePage();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
    }

    @RequestMapping(value = "/articles", method = RequestMethod.POST)
    @ResponseBody
    public String insert(Article article, String content, Long timeInMillis, HttpServletRequest request) {
    	final String _timeInMillis = timeInMillis.toString(); 
    	LOGGER.debug("timeInMillis:{}", _timeInMillis);
    	final String _tempArticleFileName = (String)request.getSession().getAttribute(_timeInMillis);	//获取已上传文件名
    	article.setContent(content.getBytes());
    	articleService.save(article);
    	
    	if (null != _tempArticleFileName) {
    		try {
    			FileUpload.copyFileNIO(new File(_articleTempDir + File.separator + _tempArticleFileName), new File(_articleDir + File.separator + article.getId() + ".jpg"));
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	
    	request.getSession().setAttribute(_timeInMillis, null);		//清空文件上传的临时路径
    	
    	final Article _article = article;
        new Thread(new Runnable() {							//生成静态页，失败，不影响数据的准确性
			public void run() {
				try {
					templateService.buildArticle(_article.getId());
					Column column = columnService.findOne(_article.getColumn().getId());
					templateService.buildTwoColumn(column.getId());
					templateService.buildOneColumn(column.getParent().getId());
					templateService.buildHomePage();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
    	
        return article.getId();
    }

    @RequestMapping(value = "/articles/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable("id") String id) {
        articleService.delete(id);
        new File(_articleDir + File.separator + id + ".jpg").delete();
    }
    
    @RequestMapping(value = "/articles/fileupload", method = RequestMethod.POST)
    @ResponseBody
    public void fileupload(@RequestParam(value = "file", required = true) MultipartFile file, Long timeInMillis, HttpServletRequest request) {
    	LOGGER.debug("url:{}, fileName:{}, fileUrl:", "/articles/fileupload", file.getName(), file.getOriginalFilename());
    	LOGGER.debug("timeInMillis:{}", timeInMillis.toString());
    	String _tempName = FileUpload.uploadImages(file,  _articleTempDir);
    	request.getSession().setAttribute(timeInMillis.toString(), _tempName);		//路径保存到Session
    }
    
    @RequestMapping(value = "/articles/generateStaticPage", method = RequestMethod.GET)
    @ResponseBody
    public void generateStaticPage() {
    	List<Article> articles = articleService.findAll();
    	try {
    		for (Article article : articles) {
    			templateService.buildArticle(article.getId());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @RequestMapping(value = "/articles/{id}/generateStaticPage", method = RequestMethod.GET)
    @ResponseBody
    public void generateStaticPage(@PathVariable("id")String id) {
    	try {
    		templateService.buildArticle(id);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @RequestMapping(value = "/articles/{id}/hideStaticPage", method = RequestMethod.GET)
    @ResponseBody
    public void hideStaticPage(@PathVariable("id")String id) {
    	LOGGER.debug(System.getProperty("webapp.root") + File.separator + "article" + File.separator + id + ".html");
    	new File(System.getProperty("webapp.root") + File.separator + "article" + File.separator + id + ".html").delete();
    }
    
    @Value("#{properties.getProperty('_articleDir')}")
    private String _articleDir;			//保存路径
    
    @Value("#{properties.getProperty('_articleTempDir')}")
    private String _articleTempDir;			//保存路径
    
    
    public String get_articleTempDir() {
		return _articleTempDir;
	}
	public void set_articleTempDir(String _articleTempDir) {
		this._articleTempDir = _articleTempDir;
	}
	public String get_articleDir() {
		return _articleDir;
	}

	public void set_articleDir(String _articleDir) {
		this._articleDir = _articleDir;
	}

	@Resource
    ArticleService articleService;

    @Resource
    ColumnService columnService;
    
    @Resource
    TemplateService templateService;

}
