package com.iokays.column.repository.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;
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
        Column column_one = new Column();
        column_one.setName("一级栏目");
        column_one.setGrade(Column.Grade.one);
        column_one.setDescription("描述:一级栏目");
        column_one.setSort(0);

//		columnService.save(column_one);

        Column column_two = new Column();
        column_two.setName("二级栏目");
        column_two.setGrade(Column.Grade.two);
        column_two.setDescription("描述:二级栏目");
        column_two.setSort(0);
        column_two.setParent(column_one);

//		columnService.save(column_two);


    }

    @Test
    public void testDelete() {
//		columnService.delete("b05d3ee8-881d-4b41-b180-1e4786237e59");
    }

    @Resource
    private ColumnService columnService;

}
