package com.mock.base.util;

import java.text.DecimalFormat;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NumberUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(NumberUtil.class);
	
	public static Double toDouble(Object val) {
		
		if (null == val) return null;
		try {
			return Double.parseDouble(String.valueOf(val));
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
	
	public static Long toLong(Object val) {
		
		if (null == val) return null;
		try {
			return Long.parseLong(String.valueOf(val));
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
	
	public static Integer toInteger(Object val) {
		
		if (null == val) return null;
		try {
			return Integer.parseInt(String.valueOf(val));
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
	
	public static Double randomDouble(Double low,Double high) {
		Random ran = new Random();
		DecimalFormat df = new DecimalFormat("0.00");
		return toDouble(df.format(ran.doubles(0.75, 1.0).findFirst().getAsDouble()));
	}
	
	public static String formateDouble(Integer scale,Double value) {
		if (null == value) return "0.00";
		if (0 == scale) return String.valueOf(Math.floor(value));
		String pattern = "0.";
		for (int i = 0;i < scale;i++) {
			pattern += "0";
		}
		pattern +="#";
		DecimalFormat df = new DecimalFormat(pattern);
		String valueStr =  df.format(value);
		int dotIndex = valueStr.indexOf(".");
		try {
			return valueStr.substring(0, dotIndex + scale + 1);
		}catch (Exception e) {
			return valueStr;
		}
	}
	
	public static void main(String[] args) {
		Double value = 5000.31415926789456;
		for(int i=0; i < 10; i++ ) {
			System.out.println(formateDouble(i, value));
		}
	}
}
