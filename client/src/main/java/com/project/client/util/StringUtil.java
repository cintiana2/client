package com.project.client.util;

import java.util.regex.Pattern;

public class StringUtil {
	
	public static boolean isValid(String value) {
		return value !=null && !value.isBlank();
	}
	
	public static String cleanLettersAndSimbols (String word) {
		 word = word.replaceAll("[^\\d]", "");
		 return word;
	}
	
	public static boolean isValidEmail(String emailAddress) {
		String patternRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
		
	    return Pattern.compile( patternRegex)
	      .matcher(emailAddress)
	      .matches();
	}

}
