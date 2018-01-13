package com.cms.szy.repository.dao;

import java.util.List;

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
	List<Long> queryDeptByRoleId(Long roleId);
	
	
	
}
