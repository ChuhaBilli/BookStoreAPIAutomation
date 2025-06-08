package com.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonUtils {

    private static final Logger logger = LoggerFactory.getLogger(CommonUtils.class);

    public static String randomString(int length, boolean useLetters, boolean useNumbers) {
        
    	logger.info("genetarting randomg string");
    	String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
        return generatedString;
    }


}
