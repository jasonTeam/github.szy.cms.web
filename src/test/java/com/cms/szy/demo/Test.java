package com.cms.szy.demo;


public class Test {
	
	public static void main(String[] args){
		char[] pwd = "admin123456".toCharArray();
		
		
		if(pwd != null){
			for(int i = 0; i < pwd.length; i++){
				pwd[i] = 0x00;
			}
			pwd = null;
		}
		System.out.println(pwd);
	}
	
}
