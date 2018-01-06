package com.cms.szy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cms.szy.entity.po.Menu;
import com.cms.szy.repository.dao.MenuRepositoryDao;
import com.cms.szy.service.MenuService;

@Service("menuService")
public class MenuServiceImpl implements MenuService{

	@Autowired
	private MenuRepositoryDao menuRepositoryDao;
	
	
	@Override
	public List<Menu> menuList() {
		
		List<Menu> menuList = menuRepositoryDao.menuList();
		for(Menu menu : menuList){
			System.out.println(menu.getMenuName());
		}
		
		return menuList;
	}
	
	
	
	
}
