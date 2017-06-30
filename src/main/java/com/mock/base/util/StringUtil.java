package com.mock.base.util;

import java.io.IOException;
import java.io.InputStreamReader;

public class StringUtil {

    public static boolean isEmpty(String str) {
        if (null == str || "".equals(str)) {
        	return true;
        }
        return false;
    }

    public static boolean isNotEmpty(String str) {
    	return !isEmpty(str);
    }
    
    public static String objToString(Object value) {
    	if (null == value) return null;
    	return value.toString().trim();
    }
    
    public static String readFromInputStreamReader(InputStreamReader input) {
        StringBuilder sb = new StringBuilder();
        char[] buffer = new char[4096];
        int charsRead;
        try {
			while ((charsRead = input.read(buffer)) >= 0) {
			    sb.append(buffer, 0, charsRead);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			input.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return sb.toString();
    }
}