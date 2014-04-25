package com.iokays.article.repository;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.iokays.column.repository.ColumnRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class ArticleRepositoryTest {

    /*@Test
    @Transactional
    public void testDelete() {
    	List<Map<String, Object>> result = articleRepository.findAllForMap();
    	for (int i = 0; null != result && i < result.size(); ++i) {
    		System.out.println("i:" + i + "--start.");
    		System.out.println(result.get(i).get("id"));
    		System.out.println(result.get(i).get("title"));
    		System.out.println("i:" + i + "--end.\n");
    	}
    }*/
    
    @Test
    public void test() {
    	
    	articleRepository.delete("3eb0ce48-9d1d-47e7-8ebc-aad443fd0df6");
    }

    @Resource
    ArticleRepository articleRepository;
    
    @Resource
    ColumnRepository columnRepository;


}
