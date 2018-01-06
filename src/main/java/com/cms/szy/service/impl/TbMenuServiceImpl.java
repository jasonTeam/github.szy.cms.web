package com.cms.szy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cms.szy.entity.po.TbMenu;
import com.cms.szy.repository.dao.TbMenuRepositoryDao;
import com.cms.szy.service.TbMenuService;

@Service("tbMenuService")
public class TbMenuServiceImpl implements TbMenuService{
	
	@Autowired
	private TbMenuRepositoryDao tbMenuRepositoryDao;
	
	
	@Override
	public List<TbMenu> listAllSubMenu() {
		return tbMenuRepositoryDao.listAllSubMenu();
	}
	
}
