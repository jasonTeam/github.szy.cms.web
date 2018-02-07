package com.cms.szy.tools.form;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.cms.szy.configuration.log.GwsLogger;
import com.cms.szy.entity.po.Role;
import com.cms.szy.entity.po.User;
import com.cms.szy.tools.exception.RRException;
import com.cms.szy.tools.regexUtil.Verify;


/**
 * 
 * 由于项目中很多的插入和修改功能的表单
 * 校验是重复的，这里提取出来方便复用
 *【表单验证类】  
 * @author ShenZiYang 
 * @date 2018年2月3日 下午4:26:18
 */
public class VerifyForm {
	
	/**
	 * 
	 *【用户表单提交校验】 
	 * @param user
	 * @return Ret返回类型   
	 * @author ShenZiYang
	 * @date 2018年2月3日下午4:39:43
	 * @throws 异常
	 */
	public static void isUserNull(User user){
		boolean isTrue = null == user.getDeptId() || StringUtils.isBlank(user.getUserName())
				  								  || StringUtils.isBlank(user.getPassword()) 
				  								  || StringUtils.isBlank(user.getEmail())
				  								  || StringUtils.isBlank(user.getMobile());
		if(isTrue){
			GwsLogger.error("新增用户参数为空:user={}",JSON.toJSON(user));
			throw new RRException("必要参数不能为空");
		}
		
		//角色校验
		if (CollectionUtils.isEmpty(user.getRoleIdList())) {
			GwsLogger.error("新增用户角色ID为空:roleList={}", JSON.toJSON(user.getRoleIdList()));
			throw new RRException("角色不能为空!");
		}
		
		//邮箱格式校验
		if (!Verify.isEmail(user.getEmail())) {
			GwsLogger.error("新增用户邮箱格式错误:email={}", JSON.toJSON(user.getEmail()));
			throw new RRException("邮箱格式不正确!");
		}
		
		//手机号格式校验
		if(!Verify.isPhone(user.getMobile())){
			GwsLogger.error("新增用户手机格式错误:mobile={}",JSON.toJSON(user.getMobile()));
			throw new RRException("手机格式不正确!");
		}
		
	}
	
	
	/**
	 * 
	 *【添加、修改角色表单校验】
	 * @Title isRoleNull 
	 * @param role void返回类型   
	 * @author ShenZiYang
	 * @date 2018年2月4日下午8:35:42
	 * @throws  异常
	 */
	public static void isRoleNull(Role role){
		//角色名是否为空
		if(StringUtils.isBlank(role.getRoleName())){
			GwsLogger.error("修改角色时角色名为空:roleName={}",JSON.toJSON(role.getRoleName()));
			throw new RRException("角色名不能为空!");
		}
		
		//所属部门是否为空
		if (null == role.getDeptId() || role.getDeptId() < 0) {
			GwsLogger.error("修改角色时部门名为空:deptName={}",JSON.toJSON(role.getDeptId()));
			throw new RRException("所属部门不能为空!");
		}
		
		// 功能权限为空判断
		if (null == role.getMenuIdList() || role.getMenuIdList().size() == 0) {
			GwsLogger.error("功能权限菜单ID为空:menuIdList={}",JSON.toJSON(role.getDeptIdList()));
			throw new RRException("功能权限未选择!");
		}

		// 数据权限为空判断
		if (null == role.getDeptIdList() || role.getDeptIdList().size() == 0) {
			GwsLogger.error("数据权限部门ID为空:deptIdList={}", JSON.toJSON(role.getDeptIdList()));
			throw new RRException("数据权限未选择!");
		}
		
	}
	
	
}
