package com.cms.szy.demo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {
	
	public static void main(String[] args){
		
		long timeStmp = 1517477412;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = dateFormat.format(new Date(timeStmp));
		System.out.println(date);
		
	}
	
}
