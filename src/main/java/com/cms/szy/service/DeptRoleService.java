package com.cms.szy.service;

import java.util.List;

public interface DeptRoleService {
	
	/**
	 * 
	 *【根据角色ID查询对应的部门Id】 
	 * @param roleId
	 * @return List<Long>返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月16日下午1:25:01
	 * @throws 异常
	 */
	List<Long> queryDeptByRoleId(Long roleId);
}
