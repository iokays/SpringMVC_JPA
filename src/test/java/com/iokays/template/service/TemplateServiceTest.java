package com.iokays.template.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.iokays.column.domain.Column;
import com.iokays.column.service.ColumnService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:rest-servlet.xml"})
public class TemplateServiceTest {

//	public void testBuildNavbar() throws IOException {
//		templateService.buildNavbar();
//	}


    @Test
    public void Test() {
        List<Column> columns = columnService.findAll();
        for (int i = 0; i < columns.size(); ++i) {
            Column column = columns.get(i);
            column.setImage(column.getImage().replace("<img src=\"", ""));
            columnService.save(column);
        }
    }

    @Resource
    TemplateService templateService;

    @Resource
    ColumnService columnService;

}
