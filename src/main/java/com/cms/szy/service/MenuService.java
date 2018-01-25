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
	
	
	/**
	 * 
	 *【根据菜单Id获取菜单实体】 
	 * @param menuId
	 * @return Menu返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月20日下午5:03:46
	 * @throws 异常
	 */
	Menu queryMenuByMenuId(Long menuId);
	
	
	/**
	 * 
	 *【根据父级菜单获取子菜单ID】 
	 * @param menuId
	 * @return Menu返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月20日下午5:37:59
	 * @throws 异常
	 */
	Menu queryChildMenuId(Long menuId);
	
	
	/**
	 * 
	 *【新增菜单】 
	 * @param menu void返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月20日下午5:04:54
	 * @throws 异常
	 */
	void saveMenu(Menu menu);
	
	
	/**
	 * 
	 *【修改菜单】 
	 * @param menu void返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月20日下午5:05:20
	 * @throws 异常
	 */
	void updateMenu(Menu menu);
	
	
	/**
	 * 
	 *【删除菜单】 
	 * @param menuId void返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月20日下午5:05:49
	 * @throws 异常
	 */
	void deleteMenuBatch(Long[] menuIds);
	
	/**
	 * 
	 *【查询不包含按钮的菜单列表】 
	 * @return List<Menu>返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月25日上午10:46:41
	 * @throws 异常
	 */
	List<Menu> queryNotButtonList();
	
	
	
}
