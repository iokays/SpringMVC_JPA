package com.iokays.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Test {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Test.class);
    public static void main(String[] args) {
    	
    	final String imgData = "data:image/jpeg;base64,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";
    	
    }
}
