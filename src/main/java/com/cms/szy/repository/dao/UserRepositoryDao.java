package com.cms.szy.repository.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.cms.szy.configuration.query.core.BaseRepository;
import com.cms.szy.entity.po.User;

public interface UserRepositoryDao extends BaseRepository<User, Long>{
	
	
	/**
	 * 
	 * (根据用户ID查询用户的所有权限) 
	 * @Title getPermsByUser 
	 * @param userId
	 * @return List<String>返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月6日下午12:26:07
	 * @throws
	 */
	@Query(value = "SELECT m.perms FROM sys_user_role ur "
			+ "LEFT JOIN sys_menu_role rm ON ur.role_id = rm.role_id "
			+ "LEFT JOIN sys_menu m ON rm.menu_id = m.menu_id WHERE ur.user_id = ?1",nativeQuery = true)
	List<String> getPermsByUser(Long userId);
	
	

	/**
	 * 
	 * 根据用户名查询用户信息) 
	 * @Title queryByUserName 
	 * @param userName
	 * @return User返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月6日下午12:54:28
	 * @throws 查询异常
	 */
	@Query("SELECT u FROM User u Where u.userName = ?1 ")
	User queryByUserName(String userName);
	
	
	
	
	
}
