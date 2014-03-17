package com.iokays.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Test {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Test.class);
    public static void main(String[] args) {
    	Integer test = 1;
    	Object object = test;
    	Integer test1= (Integer)object;
    	if (test == test1) {
			System.out.println("hello world");
		}
    }
}
