package com.cms.szy.demo;

import java.util.HashMap;
import java.util.Map;

public class Arr {
	
	private String key;
	private String value;
	
	public Arr(){ }
	
	public Arr(String key,String value){
		this.key = key;
		this.value = value;
	}
	
	public static void main(String[] args){
		
		Arr[] params = new Arr[]{new Arr("userName", "jason")};
		Map<String, Object> map = new HashMap<String,Object>();
		System.out.println(params[0].value);
		
	}

	
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
	
	
	
}
