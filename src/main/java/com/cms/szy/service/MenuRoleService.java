package com.cms.szy.service;

import java.util.List;

/**
 * 
 * (菜单角色业务层接口) 
 * @ClassName MenuRoleService 
 * @author ShenZiYang 
 * @date 2018年1月13日 下午4:35:58
 */
public interface MenuRoleService {
	
	/**
	 * 
	 * (根据角色ID获取相应的菜单按钮) 
	 * @Title queryMenuByRoleId 
	 * @param roleId
	 * @return List<Long>返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月13日下午4:38:01
	 * @throws 异常
	 */
	List<Long> queryMenuByRoleId(Long roleId);
	
	
}
