package com.cms.szy.service;

import java.util.List;

import com.cms.szy.entity.po.Dept;

/**
 * 
 *【部门业务层接口】  
 * @author ShenZiYang 
 * @date 2018年1月17日 下午3:10:18
 */
public interface DeptService {
	
	/**
	 * 
	 *【查询部门名称集合】 
	 * @return List<Dept>返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月17日下午3:40:10
	 * @throws 异常
	 */
	List<Dept> deptList();
}
