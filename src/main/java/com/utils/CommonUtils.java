package com.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class CommonUtils {


    public static String randomString(int length, boolean useLetters, boolean useNumbers) {
        String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
        return generatedString;
    }


}
