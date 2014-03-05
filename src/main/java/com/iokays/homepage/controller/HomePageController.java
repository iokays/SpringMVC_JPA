package com.iokays.homepage.controller;

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
import com.iokays.homepage.domain.HomePage;
import com.iokays.homepage.service.HomePageService;
import com.iokays.template.service.TemplateService;

@Controller
public class HomePageController {


    @RequestMapping(value = "/homePages", method = RequestMethod.GET)
    public ModelAndView listAll() {
        ModelAndView mav = new ModelAndView("homePages");
        List<HomePage> homePages = homePageService.findAll(new Sort("sort"));
        mav.addObject("homePages", homePages);
        return mav;
    }

    @RequestMapping(value = "/homePage/{id}", method = RequestMethod.GET)
    public ModelAndView findOne(@PathVariable("id") String id) {
        ModelAndView mav = new ModelAndView("homePage");
        HomePage homePage = homePageService.findOne(id);
        mav.addObject("homePage", homePage);
        return mav;
    }

    @RequestMapping(value = "/homePage/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable("id") String id) {
        return homePageService.delete(id).toString();

    }

    @RequestMapping(value = "/homePage", method = RequestMethod.GET)
    public ModelAndView toInsert() {
        ModelAndView mav = new ModelAndView("homePage");
        return mav;
    }

    @RequestMapping(value = "/homePage", method = RequestMethod.POST)
    @ResponseBody
    public String insert(HomePage homePage) {
        homePageService.save(homePage);
        try {
            templateService.buildHomePage();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return homePage.getId();
    }

    @RequestMapping(value = "/homePage/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public String update(@PathVariable("id") String id, @RequestBody String body) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        @SuppressWarnings("unchecked")
        Map<String, Object> map = objectMapper.readValue(body, Map.class);
        final String result = homePageService.update(id, map).toString();
        try {
            templateService.buildHomePage();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Resource
    HomePageService homePageService;

    @Resource
    TemplateService templateService;
}
