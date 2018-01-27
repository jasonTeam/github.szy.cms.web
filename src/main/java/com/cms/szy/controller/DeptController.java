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
 * 【部门管理控制类】
 * 
 * @author ShenZiYang
 * @date 2018年1月17日 下午3:07:19
 */
@RestController
@RequestMapping("/sys/dept")
public class DeptController extends AbstractController {

	@Autowired
	private DeptService deptService;

	/**
	 * 
	 * 【部门列表】
	 * 
	 * @Title list
	 * @return List<Dept>返回类型
	 * @author ShenZiYang
	 * @date 2018年1月27日下午10:05:54
	 * @throws 异常
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@RequiresPermissions("sys:dept:list")
	public List<Dept> list() {
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		Long startTime = System.currentTimeMillis();
		GwsLogger.info("查询部门列表信息开始:code={},message={},startTime={}", code, message, startTime);

		List<Dept> deptList = null;
		try {
			deptList = deptService.deptList();
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("查询部门列表信息异常:code={},message={},e={}", code, message, e);
		}

		Long endTime = System.currentTimeMillis() - startTime;
		GwsLogger.info("查询部门列表信息结束:code={},message={},endTime={}", code, message, endTime);
		return deptList;

	}

	/**
	 * 
	 * 【获取上级部门(管理员则为0)】
	 * 
	 * @Title info
	 * @return Ret返回类型
	 * @author ShenZiYang
	 * @date 2018年1月27日下午10:13:26
	 * @throws 异常
	 */
	@RequestMapping("/info")
	@RequiresPermissions("sys:dept:list")
	public Ret info() {
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		Long startTime = System.currentTimeMillis();
		GwsLogger.info("获取上级部门信息开始:code={},message={},startTime={}", code, message, startTime);

		long deptId = 0;
		try {
			if (getUserId() != Constant.SUPER_ADMIN) {
				deptId = deptService.getDeptByDeptId(getDeptId()).getParentId();
				GwsLogger.info("上级部门ParentId：deptId={}", deptId);
			}
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("获取上级部门信息异常:code={},message={},e={}", code, message, e);
		}

		Long endTime = System.currentTimeMillis() - startTime;
		GwsLogger.info("获取上级部门菜单树信息结束:code={},message={},endTime={}", code, message, endTime);
		return Ret.ok().put("deptId", deptId);

	}

	/**
	 * 生成部门信息菜单树,用于添加和修改功能 【选择部门菜单树】
	 * 
	 * @Title select
	 * @return Ret返回类型
	 * @author ShenZiYang
	 * @date 2018年1月27日下午10:20:22
	 * @throws 异常
	 */
	@RequestMapping("/select")
	@RequiresPermissions("sys:dept:select")
	public Ret select() {
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		Long startTime = System.currentTimeMillis();
		GwsLogger.info("选择部门菜单树信息开始:code={},message={},startTime={}", code, message, startTime);

		List<Dept> deptList = null;
		try {
			deptList = deptService.deptList();
			// 添加一级部门
			if (getUserId() == Constant.SUPER_ADMIN) {
				Dept root = new Dept();
				root.setDeptId(0L);
				root.setName(Constant.LEVEL_DEPT);
				root.setParentId(-1L);
				root.setOpen(true);
				deptList.add(root);
			}
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("选择部门菜单树信息异常:code={},message={},e={}", code, message, e);
		}

		Long endTime = System.currentTimeMillis() - startTime;
		GwsLogger.info("选择部门菜单树信息结束:code={},message={},endTime={}", code, message, endTime);
		return Ret.ok().put("deptList", deptList);
	}

	/**
	 * 
	 * 【新增部门】
	 * 
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
	 * 根据部门ID获取信息,用于修改页面回显 【根据部门ID获取信息】
	 * 
	 * @param deptId
	 * @return Ret返回类型
	 * @author ShenZiYang
	 * @date 2018年1月19日上午11:38:36
	 * @throws 异常
	 */
	@RequestMapping("/info/{deptId}")
	@RequiresPermissions("sys:dept:info")
	public Ret info(@PathVariable("deptId") Long deptId) {
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		Long startTime = System.currentTimeMillis();
		GwsLogger.info("根据部门ID获取信息开始:code={},message={},startTime={}", code, message, startTime);

		// 参数校验
		if (null == deptId || deptId < 0) {
			GwsLogger.error("部门ID为空:deptId={}", deptId);
			Ret.error("部门ID不能为空!");
		}

		Dept dept = null;
		try {
			dept = deptService.getDeptByDeptId(deptId);
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("根据部门ID获取信息异常:code={},message={},e={}", code, message, e);
		}

		Long endTime = System.currentTimeMillis() - startTime;
		GwsLogger.info("根据部门ID获取信息结束:code={},message={},endTime={}", code, message, endTime);
		return Ret.ok().put("dept", dept);
	}

	/**
	 * 
	 * 【修改部门】
	 * 
	 * @Title update
	 * @param dept
	 * @return Ret返回类型
	 * @author ShenZiYang
	 * @date 2018年1月27日下午10:31:39
	 * @throws 异常
	 */
	@RequestMapping("/update")
	@RequiresPermissions("sys:dept:update")
	public Ret update(@RequestBody Dept dept) {
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		Long startTime = System.currentTimeMillis();
		GwsLogger.info("修改部门信息开始:code={},message={},startTime={}", code, message, startTime);

		try {
			deptService.updateDept(dept);
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("修改部门信息异常:code={},message={},e={}", code, message, e);
		}

		Long endTime = System.currentTimeMillis() - startTime;
		GwsLogger.info("修改部门信息结束:code={},message={},endTime={}", code, message, endTime);
		return Ret.ok();
	}

	/**
	 * 
	 * 【删除部门】
	 * 
	 * @param deptId
	 * @return R返回类型
	 * @author ShenZiYang
	 * @date 2018年1月20日下午3:16:51
	 * @throws 异常
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@RequiresPermissions("sys:dept:delete")
	public Ret delete(Long deptId) {
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		Long startTime = System.currentTimeMillis();
		GwsLogger.info("删除部门信息开始:code={},message={},startTime={}", code, message, startTime);

		// 参数校验
		if (null == deptId || deptId < 0) {
			GwsLogger.error("部门ID为空:deptId={}", deptId);
			Ret.error("部门ID不能为空!");
		}

		// 判断是否有子部门
		List<Long> deptList = deptService.getChildDeptId(deptId);
		if (null != deptList && deptList.size() > 0) {
			return Ret.error("请先删除子部门");
		}

		try {
			deptService.deleteDept(deptId);
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("删除部门信息异常:code={},message={},e={}", code, message, e);
		}

		Long endTime = System.currentTimeMillis() - startTime;
		GwsLogger.info("删除部门信息结束:code={},message={},endTime={}", code, message, endTime);
		return Ret.ok();
	}

}
