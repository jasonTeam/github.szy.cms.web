package com.cms.szy.learn.collections;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.collections.CollectionUtils;

/*
 * List列表
 *     LinkedList - 基于链表的List
 *     ArrayList - 基于数组的List
 *     SubList - 一个List的视图
 *     Vector - 一个线程安全的List
 */
public class Lists {
	
	public static void main(String[] args){
		
		/*********************************
		 * 1.LinkedList - 基于链表的List
		 * 2.在内存中的分配非必须连续
		 * 3.插入,删除执行快，只要修改前后指针就可以了
		 * 4.查询数据慢，必须从第一个元素开始遍历
		 *********************************/
		LinkedList<String> linkedList = new LinkedList<>();
		
		//在链表后添加一个元素
		linkedList.add("aaa");
		linkedList.add("bbb");
		linkedList.add("ccc");
		
		//在链表头部插入一个元素
		linkedList.addFirst("first");
		
		//在链表尾部插入一个元素
		linkedList.addLast("last");
		
		//在指定位置插入一个元素
		linkedList.add(2, "insert");
		
		// 还有remove方法...
		
		//打印LinkedList
		if(linkedList != null && linkedList.size() > 0){
			for(String linked : linkedList){
				System.out.println(linked);
			}
		}
		
		
		/**
		 * 1.ArrayList - 基于数组的List
		 * 2.数组是线性的数据结构，在内存中是线性分配空间
		 * 3.插入，删除效率慢，因为需要移动元素
		 */
		ArrayList<String> arrayList = new ArrayList<>();
		//添加一个元素
		arrayList.add("1111");
		arrayList.add("2222");
		arrayList.add("3333");
		
		//打印ArrayList
		if(CollectionUtils.isNotEmpty(arrayList)){
			for(String array : arrayList){
				System.out.println(array);
			}
		}
		
		
		/**
		 * 1.SubList - 一个List的视图
		 */
		List<Integer> testList = new ArrayList<>();
		testList.add(6666);
		testList.add(7777);
		testList.add(8888);
		testList.add(9999);
		if(CollectionUtils.isNotEmpty(testList)){
			for(Integer test : testList){
				System.out.println("the orginal list: "+test);
			}
		}
		
		//subList
		List<Integer> sub = testList.subList(1,3);
		sub.remove(1);
		
		if(CollectionUtils.isNotEmpty(testList)){
			for(Integer test : testList){
				System.out.println("the orginal list after sublist modified: "+test);
			}
		}
		
		
		/**
		 *  1.Vector(向量类) - 一个线程安全的List
		 *  Vector继承了AbstractList，实现了List，它是一个队列，因此实现了相应的添加、删除、修改、遍历等功能。
		 *  Vector实现了RandomAccess接口，因此可以随机访问。
		 *  Vector实现了Cloneable，重载了clone()方法，因此可以进行克隆。
		 *  Vector实现了Serializable接口，因此可以进行序列化
		 *  Vector的操作是线程安全的。 vector方法加synchronized修饰（同步 将带来性能的损耗）
		 *  
		 *  Vector的数据结构和ArrayList差不多，包含了3个成员变量：elementData，elementCount，capacityIncrement。
		 *  （1）elementData是Object[]的数组，初始大小为10，会不断的增长。
		 *  （2）elementCount是元素的个数。
		 *  （3）capacityIncrement是动态数组增长的系数。
		 */
		
		Vector<String> vector = new Vector<>();
		//添加数据
		vector.add("kkkk");
		vector.addElement("LLLL");
		vector.add("ZZZZ");
		vector.add("xxxx");
		
		//for打印
		for(String ve : vector){
			System.out.println(ve);
		}
		
		//Enumeration打印2
		Enumeration<String> enu = vector.elements();
		while(enu.hasMoreElements()){
			System.out.println("nextElement():"+enu.nextElement());
		}
		
		
		
	}
	
	
	
	
	
}
