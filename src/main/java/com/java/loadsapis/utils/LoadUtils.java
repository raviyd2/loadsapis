package com.java.loadsapis.utils;

import java.util.UUID;

public class LoadUtils {
	public static Boolean validUUID(String shipperId){
		try {
			UUID.fromString(shipperId);
	        return Boolean.TRUE;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return Boolean.FALSE;
		
	}

}
