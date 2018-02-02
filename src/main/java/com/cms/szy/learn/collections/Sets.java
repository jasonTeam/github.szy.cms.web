package com.cms.szy.learn.collections;

import java.util.HashSet;
import java.util.TreeSet;

/**
 * 
 * Set：一个不允许重复数据的集合
 * 	
 * @author ShenZiYang 
 * @date 2018年2月2日 下午1:45:21
 */
public class Sets {
	
	public static void main(String[] args){
		
		/*
		 * 1.HashSet,是对Set的实现，
		 * 2.只能存储不重复的对象
		 */
		HashSet<String> set = new HashSet<String>();
		set.add("语文");
		set.add("语文");
		set.add("语文");
		set.add("语文");
//		set.add("数学");
//		set.add("英语");
//		set.add("历史");
//		set.add("政治");
//		set.add("地理");
//		set.add("生物");
//		set.add("化学");
//		set.add("化学");
		
		for(String str : set){
			System.out.println(str);
		}
		
		
		/*
		 * 1.TreeSet-有序的集合，它的作用是提供有序的Set集合
		 * 2.TreeSet是基于TreeMap实现的。TreeSet中的元素支持2种排序方式：
		 * 	  自然排序 或者 根据创建TreeSet 时提供的 Comparator 进行排序
		 */
		TreeSet<String> ts = new TreeSet<>();
		ts.add("D");
		ts.add("C");
		ts.add("A");
		ts.add("B");
		
		//打印
		for(String t : ts){
			System.out.println(t);
		}
		
		
	}
	
	
}
