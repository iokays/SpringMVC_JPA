package com.iokays.homepage.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.iokays.utils.fileupload.FileUpload;

/**
 * 首页图片管理 控制类
 * @author pengyuanbing@gmail.com
 *
 */
@Controller
public class HomePageController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HomePageController.class);

	/**
	 * 首页图片列表
	 * @return 
	 */
    @RequestMapping(value = "/homePages", method = RequestMethod.GET)
    public ModelAndView listAll(HttpServletRequest request) {
    	HttpSession session = request.getSession();
    	System.out.println(session.getId());
    	LOGGER.debug("requst:", "/homePages");
        ModelAndView mav = new ModelAndView("homePages");
        List<HomePage> homePages = homePageService.findAll(new Sort("sort"));
        mav.addObject("homePages", homePages);
        return mav;
    }

    /**
     * 根据首页图片的ID,获取图片信息
     * @param id 首页图片的ID
     * @return
     */
    @RequestMapping(value = "/homePages/{id}", method = RequestMethod.GET)
    public ModelAndView findOne(@PathVariable("id") String id) {
    	LOGGER.debug("id:{}", id);
        ModelAndView mav = new ModelAndView("homePage");
        HomePage homePage = homePageService.findOne(id);
        mav.addObject("homePage", homePage);
        return mav;
    }
    
    /**
     * 根据首页图片的ID,删除图片信息
     * @param id 首页图片的ID
     * @return
     */
    @RequestMapping(value = "/homePages/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable("id") String id) {
    	LOGGER.debug("id:{}", id);
        homePageService.delete(id);
        new File(_homeDir + File.separator + id + ".jpg").delete();
    }
    
    /**
     * 显示添加页面
     * @return
     */
    @RequestMapping(value = "/homePages/new", method = RequestMethod.GET)
    public ModelAndView homePage() {
        ModelAndView mav = new ModelAndView("homePage");
        return mav;
    }
    
    /**
     * 添加首页图片
     * @param homePage
     * @param request
     * @return
     */
    @RequestMapping(value = "/homePages", method = RequestMethod.POST)
    @ResponseBody
    public String insert(HomePage homePage, Long timeInMillis, HttpServletRequest request) {
    	homePage = homePageService.save(homePage);			//保存
    	
    	final String _timeInMillis = timeInMillis.toString();
    	final String _tempHomeFileName = (String)request.getSession().getAttribute(_timeInMillis);	//获取已上传文件名
    	
    	if (null != _tempHomeFileName) {
    		try {
				FileUpload.copyFileNIO(new File(_homeTempDir + File.separator + _tempHomeFileName), new File(_homeDir + File.separator + homePage.getId() + ".jpg"));
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	
        request.getSession().setAttribute(_timeInMillis, null);		//清空文件上传的临时路径
        LOGGER.debug("Session:End:_tempHomePath:{}", (String)request.getSession().getAttribute(_timeInMillis));
       
        new Thread(new Runnable() {
			public void run() {
				try {
					templateService.buildHomePage();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
      
        return homePage.getId();
    }
    
    /**
     * 根据首页ID，更新首页信息
     * @param id
     * @param body
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/homePages/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public void update(@PathVariable("id") String id, @RequestBody String body, HttpServletRequest request) throws IOException {
    	ObjectMapper objectMapper = new ObjectMapper();
        @SuppressWarnings("unchecked")
        Map<String, Object> map = objectMapper.readValue(body, Map.class);
        final String _timeInMillis = map.get("timeInMillis").toString();
        LOGGER.debug("timeInMillis:{}", _timeInMillis);
        map.remove(_timeInMillis);
        
        HomePage homePage = homePageService.update(id, map);
        
        final String _tempHomeFileName = (String)request.getSession().getAttribute(_timeInMillis);	//获取文件上传的临时路径
    	LOGGER.debug("Session:Start:_tempHomePath:{}", (String)request.getSession().getAttribute(_timeInMillis));
    	if (null != _tempHomeFileName) {
    		try {
    			FileUpload.copyFileNIO(new File(_homeTempDir + File.separator + _tempHomeFileName), new File(_homeDir + File.separator + homePage.getId() + ".jpg"));
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
        
        request.getSession().setAttribute(_timeInMillis, null);		//清空文件上传的临时路径
        LOGGER.debug("Session:End:_tempHomePath:{}", (String)request.getSession().getAttribute(_timeInMillis));
        
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
    public void fileupload(@RequestParam(value = "file", required = true) MultipartFile file, Long timeInMillis, HttpServletRequest request) {
    	LOGGER.debug("url:{}, fileName:{}, fileUrl:", "/homePages/fileupload", file.getName(), file.getOriginalFilename());
    	String _tempName = FileUpload.uploadImages(file,  _homeTempDir);
    	request.getSession().setAttribute(timeInMillis.toString(), _tempName);		//路径保存到Session
    }
    
    @RequestMapping(value = "/homePages/generateStaticPage")
    @ResponseBody
    public void generateStaticPage() {
    	try {
			templateService.buildHomePage();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @Resource
    HomePageService homePageService;
    
    @Resource
    TemplateService templateService;

    
    @Value("#{properties.getProperty('_homeDir')}")
    private String _homeDir;			//保存路径
    
    @Value("#{properties.getProperty('_homeTempDir')}")
    private String _homeTempDir;			//保存临时路径
    
	public String get_homeDir() {
		return _homeDir;
	}

	public void set_homeDir(String _homeDir) {
		this._homeDir = _homeDir;
	}
	
}
