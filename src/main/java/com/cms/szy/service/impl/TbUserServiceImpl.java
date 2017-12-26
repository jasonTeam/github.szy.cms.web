package com.cms.szy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cms.szy.configuration.repository.dao.TbUserRepositoryDao;
import com.cms.szy.entity.po.TbUser;
import com.cms.szy.service.TbUserService;

@Service("tbUserService")
public class TbUserServiceImpl implements TbUserService{

	@Autowired
	private TbUserRepositoryDao tbUserRepositoryDao;
	
	@Override
	public TbUser login(String userName, String userPwd) {
		return tbUserRepositoryDao.login(userName, userPwd);
	}

	@Override
	public TbUser getUserById(Long userId) {
		return tbUserRepositoryDao.findOne(userId);
	}
	
	
	
	
}
