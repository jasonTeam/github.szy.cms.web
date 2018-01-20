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
import com.cms.szy.configuration.redis.cache.IdGlobalGenerator;
import com.cms.szy.entity.po.Dept;
import com.cms.szy.entity.po.DeptRole;
import com.cms.szy.entity.po.MenuRole;
import com.cms.szy.entity.po.Role;
import com.cms.szy.entity.vo.RoleVO;
import com.cms.szy.enums.IsDeleteEnum;
import com.cms.szy.repository.dao.DeptRepositoryDao;
import com.cms.szy.repository.dao.DeptRoleRepositoryDao;
import com.cms.szy.repository.dao.MenuRoleRepositoryDao;
import com.cms.szy.repository.dao.RoleRepositoryDao;
import com.cms.szy.repository.queryFilter.RoleQueryFilter;
import com.cms.szy.service.RoleService;


@Service("roleService")
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private IdGlobalGenerator idGlobalGenerator;
	@Autowired
	private RoleRepositoryDao roleRepositoryDao;
	@Autowired
	private DeptRepositoryDao deptRepositoryDao;
	@Autowired
	private DeptRoleRepositoryDao deptRoleRepositoryDao; //部门 <-> 角色
	@Autowired
	private MenuRoleRepositoryDao menuRoleRepositoryDao; //菜单 <-> 角色
	
	
	@Override
	public Page<Role> findPageRole(RoleVO vo,Integer pageNo, Integer pageSize, String sortField) {
		
		//查询条件
		RoleQueryFilter query = new RoleQueryFilter();
		if(StringUtils.isNotEmpty(vo.getRoleName())){
			query.setRoleName(vo.getRoleName());
		}
		
		//过滤掉是否删除字段
		query.setIsDelete(IsDeleteEnum.UN_DELETE.getVal());
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
			role.setDeptName(roleDeptMap.get(role.getDeptId()).getName());
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
			role.setDeptName(roleDeptMap.get(role.getDeptId()).getName()); //获取部门名称
		}
		
		return roleList;
	}

	
	@Override
	public Role queryByRoleId(Long roleId) {
		return roleRepositoryDao.findOne(roleId);
	}

	
	@Override
	public void updateRole(Role role) {
		Role roleBean = roleRepositoryDao.findOne(role.getRoleId()); //查询是否已经存在
		if(null != roleBean){
			roleBean.setRoleName(roleBean.getRoleName());  //角色名称
			roleBean.setDeptId(roleBean.getDeptId()); //所属部门
			roleBean.setRemark(roleBean.getRemark());  //备注
		}
	}


	@Override
	public void deleteRoleBatch(Long[] roleIds) {
		if(null != roleIds && roleIds.length > 0){
			for(int i = 0; i < roleIds.length; i++){
				Role role = roleRepositoryDao.findOne(roleIds[i]);
				role.setIsDelete(IsDeleteEnum.DELETE.getVal()); //标记为删除
			}
		}
	}


	@Override
	public void saveRole(Role role) {
		Role newRole = new Role();
		newRole.setRoleName(role.getRoleName());  //角色名称
		newRole.setDeptId(role.getDeptId());      //所属部门
		newRole.setRemark(role.getRemark()); //备注
		roleRepositoryDao.save(newRole);
		
		//保存菜单与角色的关系
		List<Long> menuIdList = role.getMenuIdList();
		if (null != menuIdList && menuIdList.size() > 0) {
			for(Long menuId : menuIdList){
				MenuRole newMenuRole = new MenuRole();
				newMenuRole.setId(idGlobalGenerator.getSeqId(MenuRole.class));
				newMenuRole.setMenuId(menuId); // 菜单ID
				newMenuRole.setRoleId(newMenuRole.getRoleId()); // 角色ID
				menuRoleRepositoryDao.save(newMenuRole);
			}
		}
		 
		
		//保存部门与角色的关系
		role.getDeptIdList();
		DeptRole newDeptRole = new DeptRole();
		newDeptRole.setId(idGlobalGenerator.getSeqId(DeptRole.class));
		//newDeptRole.setDeptId(deptId);  //部门ID
		newDeptRole.setRoleId(newDeptRole.getRoleId());  //角色ID
		deptRoleRepositoryDao.save(newDeptRole);
		
	}
	

}
