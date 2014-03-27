package com.iokays.column.repository.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.iokays.column.domain.Column;
import com.iokays.column.service.ColumnService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:rest-servlet.xml"})
public class ColumnServiceTest {

    @Test
    public void testSave() {
    	Column column = columnService.findOne("796aa463-98e2-4522-b75f-e031ca315e68");
    	column.getParent().getId();
//    	System.out.println("start ---------------------------------------");
//    	columnService.update("796aa463-98e2-4522-b75f-e031ca315e68", new HashMap<String, Object>());
//    	System.out.println("end ---------------------------------------");
//    	columnService.findOne("796aa463-98e2-4522-b75f-e031ca315e68");
//    	columnService.findOne("796aa463-98e2-4522-b75f-e031ca315e68");
//    	System.out.println("---------------------------------------");
    	
//        Column column_one = new Column();
//        column_one.setName("一级栏目");
//        column_one.setGrade(Column.Grade.one);
//        column_one.setDescription("描述:一级栏目");
//        column_one.setSort(0);
//        column_one.setMarking("test");
//
//		columnService.save(column_one);

      


    }

    @Test
    public void testDelete() {
//		columnService.delete("b05d3ee8-881d-4b41-b180-1e4786237e59");
    }

    @Resource
    private ColumnService columnService;

}
