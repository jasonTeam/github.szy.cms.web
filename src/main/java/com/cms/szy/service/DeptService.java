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
	
	/**
	 * 
	 *【根据部门ID获取部门实体】 
	 * @param deptId
	 * @return Dept返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月19日上午11:40:08
	 * @throws 异常
	 */
	Dept getDeptByDeptId(Long deptId);
	
	/**
	 * 
	 *【新增部门】 
	 * @param dept void返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月20日下午3:19:53
	 * @throws 异常
	 */
	void saveDept(Dept dept);
	
	/**
	 * 
	 *【修改部门】 
	 * @param dept void返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月20日下午3:20:19
	 * @throws 异常
	 */
	void updateDept(Dept dept);
	
	/**
	 * 
	 *【删除部门;逻辑删除】 
	 * @param deptId void返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月20日下午3:30:56
	 * @throws 异常
	 */
	void deleteDept(Long deptId);
	
	/**
	 * 
	 *【根据当前部门的ID查询子部门ID】
	 * 注意：当前部门的ID就是下级部门的parentId 
	 * @param deptId
	 * @return List<Long>返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月20日下午3:39:21
	 * @throws 异常
	 */
	List<Long> getChildDeptId(Long deptId);
	
	
	
	
}
