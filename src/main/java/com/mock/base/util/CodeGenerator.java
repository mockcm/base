package com.mock.base.util;

import java.util.Random;


public class CodeGenerator {
	
	public static String generate() {
		return UUIDUtil.generateUUID();
	}
	
	private static Random ran = new Random();
	public static String generateTradeNo(Long tradeProductId) {
		String nanoTime = String.valueOf(System.nanoTime());
		String ranLong = String.valueOf(ran.nextLong());
		return new StringBuilder(String.valueOf(tradeProductId))
			.append(ranLong.substring(1, 9)).append(nanoTime.substring(nanoTime.length() - 8, nanoTime.length())).toString();
		
	}
}
