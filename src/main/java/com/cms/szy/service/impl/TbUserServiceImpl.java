package com.cms.szy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cms.szy.entity.po.TbUser;
import com.cms.szy.entity.vo.TbUserVO;
import com.cms.szy.enums.UserStatusEnum;
import com.cms.szy.repository.dao.TbUserRepositoryDao;
import com.cms.szy.service.TbUserService;
import com.cms.szy.tools.date.DateUtil;

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

	@Override
	public TbUser saveUser(TbUserVO vo) {
		TbUser newUser = new TbUser();
		newUser.setUserName(vo.getUserName());
		newUser.setUserPwd(vo.getUserPwd());
		newUser.setRoleId(2);
		newUser.setUserStatus(UserStatusEnum.NORMAL.getVal());
		newUser.setCtime(DateUtil.currentSecond());
		return tbUserRepositoryDao.save(newUser);
	}
	
	
	
	
}
