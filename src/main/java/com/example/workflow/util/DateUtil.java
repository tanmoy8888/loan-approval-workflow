package com.example.workflow.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public static String FORMAT_1 ="yyyy-MM-dd'T'HH:mm:ss";
	public static String FORMAT_2 ="yyyy-MM-dd";
	
	public static Date convertDateFromString(String dateStr) {
		System.out.println("dateStr------------>"+dateStr);
		Date date =null;
		try {
			if(checkFormat(FORMAT_1, dateStr)){
				date = new SimpleDateFormat(FORMAT_1).parse(dateStr);
			}
			else if(checkFormat(FORMAT_1, dateStr)){
				date = new SimpleDateFormat(FORMAT_2).parse(dateStr);
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return date;
	}
	
	public static boolean checkFormat(String format,String value) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			sdf.parse(value);
			return true;
		}
		catch(Exception e) {
			System.out.println(e);
			return false;
		}
	}

}
