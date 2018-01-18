package com.cms.szy.service;

import java.util.List;

/**
 * 
 *【用户角色关系表业务层接口】
 * @ClassName UserRoleService 
 * @author ShenZiYang 
 * @date 2018年1月18日 下午9:07:54
 */
public interface UserRoleService {
	
	/**
	 * 
	 *【根据用户ID查询对应的用户角色ID】
	 * @Title queryRoleIdByUserId 
	 * @param userId
	 * @return List<Long>返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月18日下午9:08:18
	 * @throws  异常
	 */
	List<Long> queryRoleIdByUserId(Long userId);
	
}
