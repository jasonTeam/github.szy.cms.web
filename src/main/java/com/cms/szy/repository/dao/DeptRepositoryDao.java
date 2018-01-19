package com.cms.szy.repository.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.cms.szy.configuration.query.core.BaseRepository;
import com.cms.szy.entity.po.Dept;

/**
 * 
 *【部门接口dao层接口】  
 * @author ShenZiYang 
 * @date 2018年1月17日 下午3:11:41
 */
public interface DeptRepositoryDao extends BaseRepository<Dept, Long>{
	
	/**
	 * 使用这种jpa查询时不能写实体类名，字段与数据库名字一致
	 *【查询部门实体集合】 
	 * @return List<Dept>返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月17日下午3:30:05
	 * @throws 异常
	 */
//	@Query(value="SELECT d.*,(SELECT p.dept_name FROM sys_dept p WHERE p.dept_id = d.parent_id) AS parentName FROM sys_dept d WHERE d.is_delete = 0",nativeQuery = true)
//	List<Dept> deptList();
	
	/**
	 * 
	 *【根据父ID获取父名称】
	 * 这里查询某部门的上级部门,传入的是当前部门的父级ID
	 * 再根据父级id去查询，(父级ID==部门ID) 
	 * @param parentId
	 * @return String返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月19日下午2:08:28
	 * @throws 异常
	 */
	@Query("SELECT d.deptName FROM Dept d WHERE d.deptId = ?1")
	String getParentName(Long parentId);
	
	
}
