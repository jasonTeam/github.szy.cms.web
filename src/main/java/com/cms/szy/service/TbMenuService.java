package com.cms.szy.service;

import java.util.List;

import com.cms.szy.entity.po.TbMenu;

public interface TbMenuService {
	
	/**
	 * 
	 * @Description TODO(这里用一句话描述这个方法的作用) 
	 * @Title listAllSubMenu 
	 * @return List<RoleMenu>返回类型   
	 * @author ShenZiYang
	 * @date 2017年12月19日上午10:44:23
	 * @throws
	 */
	List<TbMenu> listAllSubMenu();
	
}
