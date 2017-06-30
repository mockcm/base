package com.mock.base.util;

import java.lang.reflect.Field;

public class VOUtil {
	public static <T> T toVO(Object object,Class<T> clazz){
		
		T destObject;
		try {
			destObject = clazz.newInstance();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
			return null;
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
			return null;
		}
		
		Field[] fields = object.getClass().getDeclaredFields();
		if (null == fields || fields.length == 0) return destObject;
		for(int i = 0;i < fields.length;i++){
			Field field = fields[i];
			field.setAccessible(true);
			try {
				Field destField = clazz.getDeclaredField(field.getName());
				destField.setAccessible(true);
				Object value = field.get(object);
				destField.set(destObject, value);
			} catch (Exception e) {}
		}
		
		return destObject;
	}
	
}
