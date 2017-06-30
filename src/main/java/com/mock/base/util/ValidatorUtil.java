package com.mock.base.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorUtil {
	
	public static boolean isMobilePhone(String mobiles){  
		  
		//Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,2,3,5-9])|(17[0-9])|(147))\\d{8}$"); 
		Pattern p = Pattern.compile("^1[3|4|5|7|8][0-9]{9}$");
		Matcher m = p.matcher(mobiles);  
		return m.matches();  
	}  
}
