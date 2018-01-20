package com.cms.szy.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cms.szy.entity.po.Menu;
import com.cms.szy.repository.dao.MenuRepositoryDao;
import com.cms.szy.repository.dao.UserRepositoryDao;
import com.cms.szy.service.MenuService;



@Service("menuService")
public class MenuServiceImpl implements MenuService{

	@Autowired
	private MenuRepositoryDao menuRepositoryDao;
	@Autowired
	private UserRepositoryDao userRepositoryDao;
	
	
	@Override
	public List<Menu> menuList() {
//		List<Menu> menuList = menuRepositoryDao.menuList();
		List<Menu> menuList = menuRepositoryDao.findAll(); //获取所有的菜单集合
		
		Map<Long,String> parentNameMap = new HashMap<>();
		for(Menu menu : menuList){
			parentNameMap.put(menu.getParentId(), menuRepositoryDao.getParentName(menu.getParentId())); //根据父ID获取父菜单
			menu.setParentName(parentNameMap.get(menu.getParentId()));
		}
		
		
		return menuList;
	}


	@Override
	public List<Menu> getUserMenuList(Long userId) {
		// 系统管理员，拥有最高权限
		if (userId == 1) {
			return getAllMenuList(null);
		}
		
		//用户菜单列表
		List<Long> menuIdList = userRepositoryDao.queryAllMenuId(userId);
		return getAllMenuList(menuIdList);
	}

	
	/**
	 * 
	 * (获取所有菜单列表) 
	 * @Title getAllMenuList 
	 * @param object
	 * @return List<Menu>返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月6日下午1:46:01
	 * @throws 查询失败
	 */
	private List<Menu> getAllMenuList(List<Long> menuIdList) {
		
		//查询根菜单列表
		List<Menu> menuList = queryListParentId(0L, menuIdList);
		
		//递归获取子菜单
		getMenuTreeList(menuList, menuIdList);

		return menuList;
	}

	
	//递归获取子菜单
	private List<Menu> getMenuTreeList(List<Menu> menuList, List<Long> menuIdList) {
		List<Menu> subMenuList = new ArrayList<Menu>();
		for (Menu menu : menuList) {
			if (menu.getType() == 0) {// 目录
				menu.setList(getMenuTreeList(queryListParentId(menu.getMenuId(), menuIdList), menuIdList));
			}
			subMenuList.add(menu);
		}
		return subMenuList;
	}


	@Override
	public List<Menu> queryListParentId(Long parentId, List<Long> menuIdList) {
		
		List<Menu> menuList = queryListParentId(parentId);
		
		if(null == menuIdList){
			return menuList;
		}
		
		List<Menu> userMenuList = new ArrayList<>();
		for (Menu menu : menuList) {
			if(userMenuList.contains(menu.getMenuId())){
				userMenuList.add(menu);
			}
		}
	
		return userMenuList;
	}


	@Override
	public List<Menu> queryListParentId(Long parentId) {
		List<Menu> menuList = menuRepositoryDao.queryMenuByParentId(parentId);
		return menuList;
	}


	@Override
	public Menu queryMenuByMenuId(Long menuId) {
		return menuRepositoryDao.findOne(menuId);
	}


	@Override
	public void saveMenu(Menu menu) {
		// TODO Auto-generated method stub
	}


	@Override
	public void updateMenu(Menu menu) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void deleteMenu(Long menuId) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Menu queryChildMenuId(Long menuId) {
		return menuRepositoryDao.findOne(menuId);
	}



	
	
	
	
}
