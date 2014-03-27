package com.iokays.test;

import java.util.Calendar;

import org.springframework.data.web.PageableHandlerMethodArgumentResolver;



public class Test {
	
    public static void main(String[] args) {
    	Calendar calendar = Calendar.getInstance();
    	System.out.println(calendar.getTimeInMillis());
    	new PageableHandlerMethodArgumentResolver();
    }
}
