package com.cms.szy.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cms.szy.entity.po.Role;
import com.cms.szy.entity.vo.RoleVO;
import com.cms.szy.service.DeptRoleService;
import com.cms.szy.service.MenuRoleService;
import com.cms.szy.service.RoleService;
import com.cms.szy.tools.result.Ret;
import com.cms.szy.tools.validator.ValidatorUtils;

/**
 * 
 * (角色管理) 
 * @ClassName RoleController 
 * @author ShenZiYang 
 * @date 2018年1月11日 下午4:36:28
 */
@RestController
@RequestMapping("/sys/role")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	@Autowired
	private MenuRoleService menuRoleService;
	@Autowired
	private DeptRoleService DeptRoleService;

	/**
	 * 
	 * (分页查询角色列表) 
	 * @Title roleList 
	 * @param vo
	 * @return Ret返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月11日下午5:57:09
	 * @throws
	 */
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	@RequiresPermissions("sys:role:list")
	public Ret roleList(RoleVO vo){
		Page<Role> pageData = roleService.findPageRole(vo, vo.getPageNo()-1, vo.getPageSize(), "roleId");
		return Ret.ok().put("page", pageData);
	}
	
	
	/**
	 * 
	 *(获取所有角色列表集合) 
	 * @Title selectRoleList 
	 * @return Ret返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月13日下午4:20:50
	 * @throws 异常
	 */
	@RequestMapping("/select")
	@RequiresPermissions("sys:role:select")
	public Ret selectRoleList(){
		List<Role> roleList = roleService.getRoleList();
		return Ret.ok().put("list", roleList);
	}
	
	/**
	 * 
	 * (角色信息,用于修改页面回显) 
	 * @Title info 
	 * @param roleId
	 * @return Ret返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月13日下午4:22:36
	 * @throws 异常
	 */
	@RequestMapping("/info/{roleId}")
	@RequiresPermissions("sys:role:info")
	public Ret roleInfo(@PathVariable("roleId") Long roleId){
		//根据角色ID获取角色实体
		Role role = roleService.queryByRoleId(roleId);
		
		//查询角色对应的菜单
		List<Long> menuIdList = menuRoleService.queryMenuByRoleId(roleId);
		role.setMenuIdList(menuIdList);
		
		//查询角色对应的部门
		List<Long> deptIdList = DeptRoleService.queryDeptByRoleId(roleId);
		role.setDeptIdList(deptIdList);
		
		return Ret.ok().put("role", role);
	}
	

	/**
	 * 
	 *【修改用户角色数据】
	 * @Title update 
	 * @param role
	 * @return Ret返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月19日下午9:42:07
	 * @throws  异常
	 */
	@RequestMapping("/update")
	@RequiresPermissions("sys:role:update")
	public Ret update(@RequestBody Role role){
		ValidatorUtils.validateEntity(role);
		roleService.updateRole(role);
		return Ret.ok();
	}
	

	//新增数据
	
	
	/**
	 * 
	 *【批量逻辑删除角色数据】
	 * @Title delete 
	 * @param roleIds
	 * @return R返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月19日下午9:41:07
	 * @throws  异常
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("sys:role:delete")
	public Ret delete(@RequestBody Long[] roleIds){
		roleService.deleteRoleBatch(roleIds);
		return Ret.ok();
	}
	
	
}
