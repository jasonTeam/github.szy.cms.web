package com.cms.szy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cms.szy.repository.dao.UserRoleRepositoryDao;
import com.cms.szy.service.UserRoleService;

/**
 * 
 *【用户角色关系表业务层实现类】
 * @ClassName UserRoleServiceImpl 
 * @author ShenZiYang 
 * @date 2018年1月18日 下午9:09:08
 */
@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService{
	
	@Autowired
	private UserRoleRepositoryDao userRoleRepositoryDao;
	
	@Override
	public List<Long> queryRoleIdByUserId(Long userId) {
		return userRoleRepositoryDao.queryRoleIdByUserId(userId);
	}

}
