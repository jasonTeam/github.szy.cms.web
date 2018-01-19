package com.cms.szy.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.cms.szy.configuration.annotation.DataFilter;
import com.cms.szy.entity.po.Dept;
import com.cms.szy.entity.po.Role;
import com.cms.szy.entity.vo.RoleVO;
import com.cms.szy.repository.dao.DeptRepositoryDao;
import com.cms.szy.repository.dao.RoleRepositoryDao;
import com.cms.szy.repository.queryFilter.RoleQueryFilter;
import com.cms.szy.service.RoleService;


@Service("roleService")
public class RoleServiceImpl implements RoleService{

	
	@Autowired
	private RoleRepositoryDao roleRepositoryDao;
	@Autowired
	private DeptRepositoryDao deptRepositoryDao;
	
	@Override
	public Page<Role> findPageRole(RoleVO vo,Integer pageNo, Integer pageSize, String sortField) {
		
		//查询条件
		RoleQueryFilter query = new RoleQueryFilter();
		if(StringUtils.isNotEmpty(vo.getRoleName())){
			query.setRoleName(vo.getRoleName());
		}
		
		//排序
		Sort sort = new Sort(Direction.DESC,sortField);
		//分页条件
		Pageable page = new PageRequest(pageNo,pageSize,sort);
		//分页查询
		Page<Role> pageData = roleRepositoryDao.findAll(query, page);
		List<Role> roleList = pageData.getContent();
		
		//role实体和dept实体的dept_id映射
		Map<Long,Dept> roleDeptMap = new HashMap<>();
		for(Role role : roleList){
			roleDeptMap.put(role.getDeptId(), deptRepositoryDao.findOne(role.getDeptId()));
		}
		
		//数据拼装
		for(Role role : roleList){
			role.setDeptName(roleDeptMap.get(role.getDeptId()).getDeptName());
		}
	
		return pageData;
	}

	@DataFilter(tableAlias = "r", user = false)
	@Override
	public List<Role> getRoleList() {
		List<Role> roleList = roleRepositoryDao.findAll();
		
		//Role与Dept映射
		Map<Long,Dept> roleDeptMap = new HashMap<>();
		for(Role role : roleList){
			roleDeptMap.put(role.getDeptId(), deptRepositoryDao.findOne(role.getDeptId()));
			role.setDeptName(roleDeptMap.get(role.getDeptId()).getDeptName()); //获取部门名称
		}
		
		return roleList;
	}

	@Override
	public Role queryByRoleId(Long roleId) {
		return roleRepositoryDao.findOne(roleId);
	}

}
