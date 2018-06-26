package com.karthik.rest.util;

public class StringUtil {
	
	public static boolean isNullOrBlank(String input) {
		if (input==null)
			return true;
		return input.isEmpty();
	}

}
