package com.iokays.article.repository;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.iokays.article.domain.Article;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:rest-servlet.xml"})
public class ArticleRepositoryTest {

    @Test
    public void testDelete() {

    }

    @Resource
    ArticleRepository articleRepository;


}
