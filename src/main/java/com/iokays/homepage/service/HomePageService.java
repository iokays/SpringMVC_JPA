package com.iokays.homepage.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Sort;

import com.iokays.homepage.domain.HomePage;

public interface HomePageService {

    public abstract List<HomePage> findAll(Sort sort);

    public abstract HomePage save(HomePage homePage);

    public abstract Integer update(Serializable id, Map<String, Object> map) throws Exception;

    public abstract Integer delete(Serializable id);

    public abstract HomePage findOne(Serializable id);

}