package com.cms.szy.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cms.szy.entity.po.Dept;
import com.cms.szy.service.DeptService;
import com.cms.szy.tools.constant.Constant;
import com.cms.szy.tools.result.Ret;


/**
 * 
 *【部门管理控制类】  
 * @author ShenZiYang 
 * @date 2018年1月17日 下午3:07:19
 */
@RestController
@RequestMapping("/sys/dept")
public class DeptController extends AbstractController{
	
	@Autowired
	private DeptService deptService;
		
	/**
	 * 
	 *【新增查询所属部门列表 & 查询部门列表集合】
	 * 数据权限树也调用此接口 
	 * @return List<Dept>返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月17日下午3:08:25
	 * @throws 异常
	 */
	@RequestMapping(value = "/list", method  = RequestMethod.GET)
	@RequiresPermissions("sys:dept:list")
	public List<Dept> list(){
		List<Dept>  deptList = deptService.deptList();
		return deptList;
	}
	
	
	/**
	 * 
	 *【上级部门Id(管理员则为0)】 
	 * @return R返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月19日上午11:44:23
	 * @throws 异常
	 */
	@RequestMapping("/info")
	@RequiresPermissions("sys:dept:list")
	public Ret info(){
		long deptId = 0;
		if(getUserId() != Constant.SUPER_ADMIN){
			Dept dept = deptService.getDeptByDeptId(getDeptId());
			deptId = dept.getParentId();
		}
		return Ret.ok().put("deptId", deptId);
	}

	
	/**
	 * 
	 *【部门信息】 
	 * @param deptId
	 * @return R返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月19日上午11:38:36
	 * @throws 异常
	 */
	@RequestMapping("/info/{deptId}")
	@RequiresPermissions("sys:dept:info")
	public Ret info(@PathVariable("deptId") Long deptId){
		Dept dept = deptService.getDeptByDeptId(deptId);
		return Ret.ok().put("dept", dept);
	}
	
		
}
