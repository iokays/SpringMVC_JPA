package com.iokays.homepage.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

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
import com.iokays.homepage.domain.HomePage;
import com.iokays.homepage.service.HomePageService;
import com.iokays.template.service.TemplateService;

@Controller
public class HomePageController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HomePageController.class);


    @RequestMapping(value = "/homePages", method = RequestMethod.GET)
    public ModelAndView listAll() {
    	LOGGER.debug("requst:", "/homePages");
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
    
    /**
     * 首页图片上传
     * @param file
     * @return
     * @throws IOException 
     * @throws IllegalStateException 
     */
    @RequestMapping(value = "/homePages/fileupload", method = RequestMethod.POST)
    @ResponseBody
    public String fileupload(@RequestParam(value = "file", required = true) MultipartFile file) throws IllegalStateException, IOException {
    	LOGGER.debug("url:{}, fileName:{}, fileUrl:", "/homePages/fileupload", file.getName(), file.getOriginalFilename());
    	String _tempPath= _homePath + File.separator + "temp";		//临时保存路径
    	LOGGER.debug("_tempPath:{}", _tempPath);
    	File _tempDir = new File(_tempPath);
    	if (!_tempDir.exists()) {			//不存在，新建之					
    		_tempDir.mkdirs();
    	}
    	
    	String fileName = file.getOriginalFilename();// 获取文件名称
        String extension = FilenameUtils.getExtension(fileName);	//获取文件后缀
        
        String _tempName = UUID.randomUUID().toString();	//UUID当作临时文件名	
        
        _tempPath += (File.separator + _tempName + "." + extension);
        
        File _target = new File(_tempPath);
        
        file.transferTo(_target);
        LOGGER.debug("return:{}", _tempPath);
    	return _tempPath;
    }

    @Resource
    HomePageService homePageService;

    @Resource
    TemplateService templateService;
    
    @Value("#{properties.getProperty('_homePath')}")
    private String _homePath;

	public String get_homePath() {
		return _homePath;
	}

	public void set_homePath(String _homePath) {
		this._homePath = _homePath;
	}
	
	public static void main(String[] args) {
		LOGGER.debug("hello world");
	}
   
}
