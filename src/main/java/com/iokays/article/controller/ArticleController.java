package com.iokays.article.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iokays.article.domain.Article;
import com.iokays.article.service.ArticleService;
import com.iokays.column.domain.Column;
import com.iokays.column.service.ColumnService;
import com.iokays.template.service.TemplateService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Map;

@Controller
public class ArticleController {

    private final static Logger logger = LoggerFactory.getLogger(ArticleController.class);

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
        try {
            templateService.buildArticle(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resutl;
    }

    @RequestMapping(value = "/articles", method = RequestMethod.POST)
    @ResponseBody
    public String insert(Article article) {
        articleService.save(article);
        try {
            templateService.buildArticle(article.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return article.getId();
    }

    @RequestMapping(value = "/articles/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable("id") String id) {
        return articleService.delete(id).toString();
    }

    @Resource
    ArticleService articleService;

    @Resource
    ColumnService columnService;

    @Resource
    TemplateService templateService;

}
