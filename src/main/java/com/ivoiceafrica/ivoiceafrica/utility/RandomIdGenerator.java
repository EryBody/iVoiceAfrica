package com.ivoiceafrica.ivoiceafrica.utility;

import java.security.SecureRandom;
import java.util.Random;

public class RandomIdGenerator {

	private static final Random generator = new Random();
	static final String SOURCE = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvwxyz";
	static SecureRandom secureRnd = new SecureRandom();

	public static String randomString(int length) {
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++)
			sb.append(SOURCE.charAt(secureRnd.nextInt(SOURCE.length())));
		return sb.toString();
	}
	
	
	public static String generateId(int length) {
		return randomString(length);
	}

}
