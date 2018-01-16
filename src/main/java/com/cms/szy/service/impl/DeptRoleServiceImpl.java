package com.cms.szy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cms.szy.repository.dao.DeptRoleRepositoryDao;
import com.cms.szy.service.DeptRoleService;

@Service("deptRoleService")
public class DeptRoleServiceImpl implements DeptRoleService{
	
	@Autowired
	private DeptRoleRepositoryDao deptRoleRepositoryDao;
	
	@Override
	public List<Long> queryDeptByRoleId(Long roleId) {
		return deptRoleRepositoryDao.queryDeptByRoleId(roleId);
	}
	
}
