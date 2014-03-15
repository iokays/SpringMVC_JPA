package com.iokays.article.controller;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iokays.article.domain.Article;
import com.iokays.article.service.ArticleService;
import com.iokays.column.domain.Column;
import com.iokays.column.service.ColumnService;

/**
 * 文章管理，控制层实现类
 */
@Controller
public class ArticleController {

    private final static Logger LOGGER = LoggerFactory.getLogger(ArticleController.class);

    /**
     *
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
    public String update(@PathVariable("id") String id, @RequestBody String body) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        @SuppressWarnings("unchecked")
        Map<String, Object> map = objectMapper.readValue(body, Map.class);
        final String resutl = articleService.update(id, map).toString();
       
        return resutl;
    }

    @RequestMapping(value = "/articles", method = RequestMethod.POST)
    @ResponseBody
    public String insert(Article article) {
    	decoderImg(article.getContent());
    	articleService.save(article);
        return article.getId();
    }

    @RequestMapping(value = "/articles/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable("id") String id) {
        return articleService.delete(id).toString();
    }
    
    private void decoderImg(String context) {
    	Pattern pattern = Pattern.compile("<img(.*)>");
    	Matcher matcher = pattern.matcher(context);
    	while (matcher.find()) {
    		System.out.println(matcher.group(0));
    	}
    	
    }

    @Resource
    ArticleService articleService;

    @Resource
    ColumnService columnService;

}
