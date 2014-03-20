package com.iokays.homepage.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iokays.homepage.domain.HomePage;
import com.iokays.homepage.repository.HomePageRepository;
import com.iokays.homepage.service.HomePageService;

@Service("homePageService")
@Transactional
public class HomePageServiceImpl implements HomePageService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HomePageServiceImpl.class);

    /* (non-Javadoc)
     * @see com.iokays.homepage.service.impl.HomePageService#findAll(org.springframework.data.domain.Sort)
     */
    @Override
    public List<HomePage> findAll(Sort sort) {
        return homePageRepository.findAll(sort);
    }

    /* (non-Javadoc)
     * @see com.iokays.homepage.service.impl.HomePageService#save(com.iokays.homepage.domain.HomePage)
     */
    @Override
    public HomePage save(HomePage homePage) {
        return homePageRepository.save(homePage);
    }
    
    /**
     * 更新首页图片
     */
    @Override
    public HomePage update(String id, Map<String, Object> map) {
    	LOGGER.debug("map:{}", map.toString());
    	HomePage _homePage = homePageRepository.findOne(id);
    	
    	if (map.containsKey("id")) { _homePage.setId(map.get("id").toString()); map.remove("id"); }
    	if (map.containsKey("name")) { _homePage.setName(map.get("name").toString()); map.remove("name"); }
    	if (map.containsKey("target")) { _homePage.setTarget(map.get("target").toString()); map.remove("target"); }
    	if (map.containsKey("sort")) { _homePage.setSort((Integer)map.get("sort")); map.remove("sort"); }		//sort,不能为空，当为空(null)或数据格式错误,抛出异常即可
    	
    	return _homePage;
    }

    /* (non-Javadoc)
     * @see com.iokays.homepage.service.impl.HomePageService#delete(java.io.Serializable)
     */
    @Override
    public void delete(String id) {
        homePageRepository.delete(id);
    }

    /* (non-Javadoc)
     * @see com.iokays.homepage.service.impl.HomePageService#findOne(java.io.Serializable)
     */
    @Override
    public HomePage findOne(String id) {
        return homePageRepository.findOne(id);
    }

    @Resource
    private HomePageRepository homePageRepository;

}
