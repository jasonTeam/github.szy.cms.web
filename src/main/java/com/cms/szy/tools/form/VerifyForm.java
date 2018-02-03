package com.cms.szy.tools.form;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.cms.szy.configuration.log.GwsLogger;
import com.cms.szy.entity.po.User;
import com.cms.szy.tools.exception.RRException;


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
	 *【表单提交校验】 
	 * @param user
	 * @return Ret返回类型   
	 * @author ShenZiYang
	 * @date 2018年2月3日下午4:39:43
	 * @throws 异常
	 */
	public static void isNull(User user){
		boolean isTrue = null == user.getDeptId() || StringUtils.isBlank(user.getUserName())
				  								  || StringUtils.isBlank(user.getPassword()) 
				  								  || StringUtils.isBlank(user.getEmail())
				  								  || StringUtils.isBlank(user.getMobile());
		if(isTrue){
			GwsLogger.error("新增用户参数为空:user={}",JSON.toJSON(user));
			throw new RRException("必要参数不能为空");
		}
	}
	
	
	
}
