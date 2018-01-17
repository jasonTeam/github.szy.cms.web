package com.cms.szy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cms.szy.entity.po.Dept;
import com.cms.szy.repository.dao.DeptRepositoryDao;
import com.cms.szy.service.DeptService;

/**
 * 
 *【部门接口业务实现类】  
 * @author ShenZiYang 
 * @date 2018年1月17日 下午3:11:14
 */
@Service("deptService")
public class DeptServiceImpl implements DeptService{
	
	@Autowired
	private DeptRepositoryDao deptRepositoryDao;
	
	@Override
	public List<Dept> deptList() {
		return deptRepositoryDao.deptList();
	}
	
}
