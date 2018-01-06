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
	
	
}
