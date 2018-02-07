package com.cms.szy.repository.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
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
//	@Query(value = "SELECT m.perms FROM sys_user_role ur "
//			+ "LEFT JOIN sys_menu_role rm ON ur.role_id = rm.role_id "
//			+ "LEFT JOIN sys_menu m ON rm.menu_id = m.menu_id WHERE ur.user_id = ?1",nativeQuery = true)
//	List<String> getPermsByUser(Long userId);
	
	
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
	
	
	/**
	 * 
	 * (根据用户ID获取用户的所有菜单ID) 
	 * @Title queryAllMenuId 
	 * @param userId
	 * @return List<Long>返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月6日下午2:18:14
	 * @throws 查询失败
	 */
//	@Query(value = "select distinct rm.menu_id from sys_user_role ur "
//			+ "LEFT JOIN sys_menu_role rm on ur.role_id = rm.role_id where ur.user_id = ?1", nativeQuery = true)
//	List<BigInteger> queryAllMenuId(Long userId);
	

	/**
	 * 
	 * (修改用户密码) 
	 * @Title updatePwd 
	 * @param userId
	 * @param newPassowrd
	 * @return int返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月8日下午2:32:44
	 * @throws 异常
	 */
	@Modifying(clearAutomatically = true)
	@Transactional 
	@Query("UPDATE User u SET u.password = ?1 WHERE u.userId = ?2 ")
	int updatePwd(String newPassowrd, Long userId);
	
	
}
