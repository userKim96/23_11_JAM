package com.koreaIT.java.JAM.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Util {
	public static String getNow() {
		
		
		LocalDateTime now = LocalDateTime.now();
		
		String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy년 MM년 dd일 HH:mm:ss"));
		
		return formatedNow;
		
	}
	
}