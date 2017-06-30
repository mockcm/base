package com.mock.base.util;

public class TypeTransferTestUtil {
	
	public static boolean isBoolean(Object param) {
		try {
			if (null == param) return false;
			Boolean.parseBoolean(String.valueOf(param));
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean isDouble(Object param) {
		try {
			if (null == param) return false;
			Double.parseDouble(String.valueOf(param));
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean isInteger(Object param) {
		try {
			if (null == param) return false;
			Integer.parseInt(String.valueOf(param));
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean isLong(Object param) {
		try {
			if (null == param) return false;
			Long.parseLong(String.valueOf(param));
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean isFloat(Object param) {
		try {
			if (null == param) return false;
			Float.parseFloat(String.valueOf(param));
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
