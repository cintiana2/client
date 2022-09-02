package com.project.client.util;

import org.apache.commons.lang3.StringUtils;

public class AddressUtil {
	
	public static boolean isValidZipcode(String zipcode) {
		
		if(StringUtils.isNumeric(zipcode) && zipcode.length() == 8) {
			return true;
		}
		
		return false;
	}
	
	public static boolean isValidState(String estate) {
		
		if(estate.length() == 2) {
			return true;
		}
		
		return false;
	}

}
