package com.cms.szy.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.alibaba.fastjson.JSONArray;

public class ListTest {
	
	public static void main(String[] args){
		
		//可用于封装接口请求参数返回
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		
		Map<String,Object> dataMap = new HashMap<>();
		dataMap.put("userId", 10001);
		dataMap.put("userName", "jack");
		dataMap.put("age", 24);
		dataMap.put("hobby", "playSport");
		
		resList.add(dataMap);
		
		//转化成json的格式打印
		System.out.println(JSONArray.toJSON(dataMap));
		
		for(Map<String,Object> res : resList){	
			//以键值对的形式打印
			System.out.println(res);
		}
		
		//集合
		List<String> dataList = new ArrayList<>(); 
		dataList.add("aaa");
		dataList.add("bbb");
		dataList.add("ccc");
		
		//判断集合是否为空   CollectionUtils
		if(CollectionUtils.isNotEmpty(dataList)){
			for(String str : dataList){
				System.out.println(str);
			}
		}
		
		Long maxNum = System.currentTimeMillis();
		System.out.println("maxNum="+maxNum);
		
		Long randNum = Math.round(Math.random() * 10);
		System.out.println("randNum="+randNum);
		
		
	}
	
	
	
}	
