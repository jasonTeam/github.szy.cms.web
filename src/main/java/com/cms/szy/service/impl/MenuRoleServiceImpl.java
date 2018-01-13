package com.cms.szy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cms.szy.repository.dao.MenuRoleRepositoryDao;
import com.cms.szy.service.MenuRoleService;

@Service("menuRoleService")
public class MenuRoleServiceImpl implements MenuRoleService{
	
	@Autowired
	private MenuRoleRepositoryDao menuRoleRepositoryDao;
	
	@Override
	public List<Long> queryMenuByRoleId(Long roleId) {
		List<Long> menuIdList = menuRoleRepositoryDao.queryMenuByRoleId(roleId);
		return menuIdList;
	}
	
	
	

}
