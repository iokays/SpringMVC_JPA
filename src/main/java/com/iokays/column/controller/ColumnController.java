package com.iokays.column.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iokays.column.domain.Column;
import com.iokays.column.service.ColumnService;
import com.iokays.template.service.TemplateService;

@Controller
public class ColumnController {
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
    public String delete(@PathVariable("id") String id) {
        return columnService.delete(id).toString();

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
    public String insert(Column column) {
        columnService.save(column);
        try {
            templateService.buildColumn(column.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return column.getId();
    }

    @RequestMapping(value = "/columns/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public String update(@PathVariable("id") String id, @RequestBody String body) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        @SuppressWarnings("unchecked")
        Map<String, Object> map = objectMapper.readValue(body, Map.class);
        final String result = columnService.update(id, map).toString();
        try {
            templateService.buildColumn(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Resource
    private ColumnService columnService;

    @Resource
    private TemplateService templateService;

}
