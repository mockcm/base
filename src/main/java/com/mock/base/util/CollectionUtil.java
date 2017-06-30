package com.mock.base.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class CollectionUtil {
	
	public static final String COMMA = ",";

	public static boolean isEmpty(Collection<?> collection) {
		if(collection == null || collection.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isNotEmpty(Collection<?> collection) {
		return !isEmpty(collection);
	}
	
	public static boolean isNotEmpty(Map<?, ?> map) {
		if(map != null && !map.isEmpty() && map.size() > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isEmpty(Map<?, ?> map) {
		return !isNotEmpty(map);
	}
	
	public static String list2String(List<?> list) {
		if (isEmpty(list)) return null;
		String strList = "";
		for (Object str : list) {
			strList += str + ",";
		}
		return strList.substring(0,strList.length() - 1);
	}
	
	public static List<String> string2List(String srcStr,String ... separator) {
		if (StringUtil.isEmpty(srcStr)) return null;
		String sepa = ",";
		if (separator != null && separator.length > 0) {
			sepa = separator[0];
		}
		List<String> strList = new ArrayList<String> ();
		for (String str : srcStr.split(sepa)) {
			strList.add(str);
		}
		return strList;
	}
}
