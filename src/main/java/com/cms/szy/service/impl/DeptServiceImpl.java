package com.cms.szy.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cms.szy.configuration.redis.cache.IdGlobalGenerator;
import com.cms.szy.entity.po.Dept;
import com.cms.szy.enums.IsDeleteEnum;
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
	@Autowired
	private IdGlobalGenerator idGlobalGenerator;
	
	
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


	@Override
	@Transactional  //添加事务
	public void saveDept(Dept dept) {
		Dept newDept = new Dept();
		newDept.setDeptId(idGlobalGenerator.getSeqId(Dept.class)); //部门ID
		newDept.setName(dept.getName()); 		  //部门名称
		newDept.setParentId(dept.getParentId());  //上级部门
		newDept.setOrderNum(dept.getOrderNum());  //排序号
		newDept.setIsDelete(IsDeleteEnum.UN_DELETE.getVal()); //是否删除
		deptRepositoryDao.save(newDept);          //保存数据
	}


	@Override
	@Transactional  //添加事务
	public void updateDept(Dept dept) {
		Dept oriDept = deptRepositoryDao.findOne(dept.getDeptId()); //先查询数据是否存在
		if(null != oriDept){
			oriDept.setName(dept.getName());  //部门名称
			oriDept.setParentId(dept.getParentId());    //上级部门
			oriDept.setOrderNum(dept.getOrderNum());  //排序号
			deptRepositoryDao.save(oriDept);
		}
	}


	@Override
	@Transactional  //添加事务
	public void deleteDept(Long deptId) {
		deptRepositoryDao.delete(deptId); //逻辑删除
	}


	@Override
	public List<Long> getChildDeptId(Long deptId) {
		return deptRepositoryDao.getChildDeptId(deptId);
	}



	
	
}
