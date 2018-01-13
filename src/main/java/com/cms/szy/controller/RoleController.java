package com.cms.szy.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cms.szy.entity.po.Role;
import com.cms.szy.entity.vo.RoleVO;
import com.cms.szy.service.RoleService;
import com.cms.szy.tools.result.Ret;

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
	 * (角色信息) 
	 * @Title info 
	 * @param roleId
	 * @return R返回类型   
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
		
		
		//查询角色对应的部门
		
		
		return null;
	}
	
	
	
	
}
