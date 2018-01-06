package com.cms.szy.service;

import java.util.List;

/**
 * 
 * (用户业务层接口类) 
 * @ClassName UserService 
 * @author ShenZiYang 
 * @date 2018年1月6日 上午9:37:08
 */
public interface UserService {
	
	/**
	 * 
	 * (根据用户ID查询用户的所有权限) 
	 * @Title getPermsByUser 
	 * @param userId
	 * @return List<String>返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月6日下午12:28:02
	 * @throws 查询失败
	 */
	List<String> getPermsByUser(Long userId);
	
	
}
