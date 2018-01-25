package com.cms.szy.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cms.szy.configuration.log.GwsLogger;
import com.cms.szy.entity.po.Dept;
import com.cms.szy.service.DeptService;
import com.cms.szy.tools.constant.CommConstant;
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
	 *【新增查询所属部门列表】
	 * 数据权限树也调用此接口 
	 * @return List<Dept>返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月17日下午3:08:25
	 * @throws 异常
	 */
	@RequestMapping(value = "/list", method  = RequestMethod.GET)
	@RequiresPermissions("sys:dept:list")
	public List<Dept> list(){
		List<Dept> deptList = deptService.deptList();
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
		if(getUserId() != Constant.ADMIN){
			Dept dept = deptService.getDeptByDeptId(getDeptId());
			deptId = dept.getParentId();
		}
		return Ret.ok().put("deptId", deptId);
	}
	
	
	/**
	 * 选择部门(添加、修改菜单)
	 */
	@RequestMapping("/select")
	@RequiresPermissions("sys:dept:select")
	public Ret select(){
		List<Dept>  deptList = deptService.deptList();
		//添加一级部门
		if(getUserId() == Constant.ADMIN){
			Dept root = new Dept();
			root.setDeptId(0L);
			root.setName(Constant.LEVEL_DEPT);
			root.setParentId(-1L);
			root.setOpen(true);
			deptList.add(root);
		}
		return Ret.ok().put("deptList", deptList);
	}
	

	/**
	 * 
	 *【新增部门】 
	 * @param dept
	 * @return Ret返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月20日下午3:15:56
	 * @throws 异常
	 */
	@RequestMapping("/save")
	@RequiresPermissions("sys:dept:save")
	public Ret save(@RequestBody Dept dept) {
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		Long startTime = System.currentTimeMillis();
		GwsLogger.info("新增部门操作开始:code={},message={},startTime={}", code, message, startTime);
		
		try {
			deptService.saveDept(dept);
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("新增部门操作异常:code={},message={},e={}", code, message, e);
			return Ret.error(e.getMessage());
		}

		Long endTime = System.currentTimeMillis() - startTime;
		GwsLogger.info("新增部门操作结束:code={},message={},endTime={}", code, message, endTime);
		return Ret.ok();

	}
	
	
	/**
	 * 
	 *【部门信息;根据部门ID获取信息,用于修改页面回显】 
	 * @param deptId
	 * @return Ret返回类型   
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
	
	
	/**
	 * 
	 *【修改部门】 
	 * @param dept
	 * @return R返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月20日下午3:16:37
	 * @throws 异常
	 */
	@RequestMapping("/update")
	@RequiresPermissions("sys:dept:update")
	public Ret update(@RequestBody Dept dept){
		deptService.updateDept(dept);
		return Ret.ok();
	}
	

	/**
	 * 
	 *【删除部门】 
	 * @param deptId
	 * @return R返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月20日下午3:16:51
	 * @throws 异常
	 */
	@RequestMapping(value = "/delete",method  = RequestMethod.POST)
	@RequiresPermissions("sys:dept:delete")
	public Ret delete(long deptId){
		//判断是否有子部门
		List<Long> deptList = deptService.getChildDeptId(deptId);
		if(null != deptList && deptList.size() > 0){
			return Ret.error("请先删除子部门");
		}
		
		deptService.deleteDept(deptId);
		return Ret.ok();
	}
	
	
	
}
