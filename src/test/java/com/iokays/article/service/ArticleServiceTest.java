package com.iokays.article.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:rest-servlet.xml"})
public class ArticleServiceTest {

    @Test
    public void testDelete() {
        articleService.delete("e7284e24-9f67-43a5-b87c-42055ecec7f7");
    }

    @Resource
    ArticleService articleService;
}
