package com.iokays.homepage.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Sort;

import com.iokays.homepage.domain.HomePage;

public interface HomePageService {

    public abstract List<HomePage> findAll(Sort sort);

    public abstract HomePage save(HomePage homePage);

    public abstract HomePage update(String id, Map<String, String> map);

    public abstract void delete(String id);

    public abstract HomePage findOne(String id);

}