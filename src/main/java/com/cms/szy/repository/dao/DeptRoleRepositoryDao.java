package com.cms.szy.repository.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cms.szy.configuration.query.core.BaseRepository;
import com.cms.szy.entity.po.DeptRole;

/**
 * 
 *【角色部门关系dao层接口】  
 * @author ShenZiYang 
 * @date 2018年1月13日 下午4:50:00
 */
public interface DeptRoleRepositoryDao extends BaseRepository<DeptRole, Long>{
	
	/**
	 * 
	 *【根据roleId获取对应的部门】 
	 * @param roleId
	 * @return List<Long>返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月13日下午4:50:56
	 * @throws 异常
	 */
	@Query("SELECT dr.deptId FROM DeptRole dr WHERE dr.roleId = ?1")
	List<Long> queryDeptByRoleId(Long roleId);
	
	 
	/**
	 * 
	 *【根据roidId删除 dept与角色的对应关系】 
	 * @param roleId void返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月22日下午4:24:43
	 * @throws 异常
	 */
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "delete from sys_dept_role WHERE role_id = ?1",nativeQuery = true)
	void deleteDeptRole(Long roleId);
	
	
}
