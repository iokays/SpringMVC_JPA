package com.iokays.homepage.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iokays.homepage.domain.HomePage;
import com.iokays.homepage.repository.HomePageRepository;
import com.iokays.homepage.service.HomePageService;

@Service("homePageService")
@Transactional
public class HomePageServiceImpl implements HomePageService {

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

    @Override
    public Integer update(Serializable id, Map<String, Object> map) throws Exception {
        return homePageRepository.update(id, map);
    }

    /* (non-Javadoc)
     * @see com.iokays.homepage.service.impl.HomePageService#delete(java.io.Serializable)
     */
    @Override
    public Integer delete(Serializable id) {
        return homePageRepository.deleteById(id);
    }

    /* (non-Javadoc)
     * @see com.iokays.homepage.service.impl.HomePageService#findOne(java.io.Serializable)
     */
    @Override
    public HomePage findOne(Serializable id) {
        return homePageRepository.findOne(id);
    }

    @Resource
    private HomePageRepository homePageRepository;

}
