package com.cms.szy.demo;

public final class StringTool {
	
	public static void main(String[] args){
		String s = "15267151846,,15267151846,15267151846";
		String[] str = s.split(",");
		
		for(String ss : str ){
			System.out.println(ss.trim());
		}
		
	}
	
	
	
}
