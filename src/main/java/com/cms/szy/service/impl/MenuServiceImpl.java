package com.cms.szy.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cms.szy.configuration.redis.cache.IdGlobalGenerator;
import com.cms.szy.entity.po.Menu;
import com.cms.szy.enums.MenuTypeEnum;
import com.cms.szy.repository.dao.MenuRepositoryDao;
import com.cms.szy.repository.dao.UserRepositoryDao;
import com.cms.szy.service.MenuService;
import com.cms.szy.tools.constant.Constant;



@Service("menuService")
public class MenuServiceImpl implements MenuService{

	@Autowired
	private MenuRepositoryDao menuRepositoryDao;
	@Autowired
	private UserRepositoryDao userRepositoryDao;
	@Autowired
	private IdGlobalGenerator idGlobalGenerator;
	
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
		if (userId == Constant.SUPER_ADMIN) {
			return getAllMenuList(null);
		}
		
		//用户菜单列表
		List<BigInteger> menuIdList = userRepositoryDao.queryAllMenuId(userId);
		
		//将BigInteger转为Long,后面将用Long做匹配
		List<Long> menuIdLists = new ArrayList<>();
		for(BigInteger menuId : menuIdList){
			menuIdLists.add(menuId.longValue());
		}
		
		return getAllMenuList(menuIdLists);
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
			if (menu.getType() == MenuTypeEnum.CATALOG.getVal()) {  // 目录
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
			if(menuIdList.contains(menu.getMenuId())){
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
	@Transactional  //添加事务
	public void saveMenu(Menu menu) {
		Menu newMenu = new Menu();
		newMenu.setMenuId(idGlobalGenerator.getSeqId(Menu.class));  //生成菜单ID
		newMenu.setParentId(menu.getParentId()); //父菜单ID
		newMenu.setName(menu.getName());  //菜单名称
		newMenu.setMenuUrl(menu.getMenuUrl()); //菜单Url
		newMenu.setPerms(menu.getPerms()); //授权
		newMenu.setType(menu.getType()); //菜单类型 (类型   0：目录   1：菜单   2：按钮)
		newMenu.setIcon(menu.getIcon()); //菜单图标
		newMenu.setSort(menu.getSort()); //排序
		menuRepositoryDao.save(newMenu);  
	}


	@Override
	@Transactional  //添加事务
	public void updateMenu(Menu menu) {
		Menu oriMenu = menuRepositoryDao.findOne(menu.getMenuId());  //1.先查询原来的数据是否存在
		if(null != oriMenu){
			oriMenu.setParentId(menu.getParentId()); //父菜单ID
			oriMenu.setName(menu.getName());  //菜单名称
			oriMenu.setMenuUrl(menu.getMenuUrl()); //菜单Url
			oriMenu.setPerms(menu.getPerms()); //授权
			oriMenu.setType(menu.getType()); //菜单类型 (类型   0：目录   1：菜单   2：按钮)
			oriMenu.setIcon(menu.getIcon()); //菜单图标
			oriMenu.setSort(menu.getSort()); //排序
			menuRepositoryDao.save(menu);
		}
	}


	@Override
	@Transactional  //添加事务
	public void deleteMenuBatch(Long[] menuIds) {
		if(null != menuIds && menuIds.length > 0){
			for(int i = 0; i < menuIds.length; i++){
				menuRepositoryDao.delete(menuIds[i]);
			}
		}
	}


	@Override
	public Menu queryChildMenuId(Long menuId) {
		return menuRepositoryDao.findOne(menuId);
	}


	@Override
	public List<Menu> queryNotButtonList() {
		return menuRepositoryDao.queryNotButtonList();
	}

}
