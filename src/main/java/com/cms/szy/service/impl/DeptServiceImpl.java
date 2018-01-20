package com.cms.szy.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

		List<Dept> deptList = deptRepositoryDao.findAll();
		Map<Long, String> parentNameMap = new HashMap<>();
		for (Dept dept : deptList) {
			String parentName = deptRepositoryDao.getParentName(dept.getParentId());
			parentNameMap.put(dept.getParentId(), parentName);
			dept.setParentName(parentNameMap.get(dept.getParentId())); //获取父级部门名称
		}
		return deptList;
	}

	
	@Override
	public Dept getDeptByDeptId(Long deptId) {
		return deptRepositoryDao.findOne(deptId);
	}
	
}
