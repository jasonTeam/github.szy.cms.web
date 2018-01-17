package com.cms.szy.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cms.szy.entity.po.Dept;
import com.cms.szy.service.DeptRoleService;
import com.cms.szy.service.DeptService;

/**
 * 
 *【部门控制类】  
 * @author ShenZiYang 
 * @date 2018年1月17日 下午3:07:19
 */
@RestController
@RequestMapping("/sys/dept")
public class DeptController {
	
	@Autowired
	private DeptService deptService;
	
	/**
	 * 
	 *【新增时所属部门列表】 
	 * @return List<Dept>返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月17日下午3:08:25
	 * @throws 异常
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:dept:list")
	public List<Dept> list(){
		List<Dept>  deptList = deptService.deptList();
		return deptList;
	}
	
	
	
}
