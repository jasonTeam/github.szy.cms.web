package com.cms.szy.service;

import java.util.List;

import com.cms.szy.entity.po.Menu;




/**
 * 
 * (菜单业务接口层) 
 * @ClassName MenuService 
 * @author ShenZiYang 
 * @date 2018年1月6日 上午10:16:37
 */
public interface MenuService {
	
	/**
	 * 
	 * (获取菜单集合) 
	 * @Title menuList 
	 * @return List<Menu>返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月6日上午10:17:04
	 * @throws 查询失败
	 */
	List<Menu> menuList();
	
	
	/**
	 * 
	 * (获取用户菜单列表) 
	 * @Title getUserMenuList 
	 * @param userId
	 * @return List<Menu>返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月6日下午1:43:19
	 * @throws 查询失败
	 */
	List<Menu> getUserMenuList(Long userId);
	
	
	/**
	 * 
	 * (根据父菜单，查询子菜单) 
	 * @Title queryListParentId 
	 * @param parentId
	 * @return List<SysMenuEntity>返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月6日下午2:07:25
	 * @throws 查询失败
	 */
	List<Menu> queryListParentId(Long parentId);
	
	
	/**
	 * 
	 * (根据父菜单查询子菜单) 
	 * @Title queryListParentId 
	 * @param parentId 父菜单ID
	 * @param menuIdList 用户菜单ID
	 * @return List<Menu>返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月6日下午1:48:28
	 * @throws 查询失败
	 */
	List<Menu> queryListParentId(Long parentId, List<Long> menuIdList);
	
	

	
	
}
