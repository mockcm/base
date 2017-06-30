package com.mock.base.util;

import java.util.UUID;



public class UUIDUtil {
    
    /**
     * 生成通用唯一识别码
     * 
     * @return
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static void main(String[] args) {
    	System.out.println(generateUUID());
    }
}