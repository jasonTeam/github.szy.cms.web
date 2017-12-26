package com.cms.szy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cms.szy.configuration.log.GwsLogger;
import com.cms.szy.entity.po.TbUser;
import com.cms.szy.entity.vo.TbUserVO;
import com.cms.szy.service.TbUserService;
import com.cms.szy.tools.constant.CommConstant;
import com.cms.szy.tools.result.RetResult;

/**
 * 
 * (用户实体控制器) 
 * @ClassName TbUserController 
 * @author ShenZiYang 
 * @date 2017年12月26日 下午4:31:47
 */
public class TbUserController {
	
	@Autowired
	TbUserService tbUserServuce;
	
	
	/**
	 * 
	 * (插入用户数据) 
	 * @Title saveUser 
	 * @param user
	 * @return RetResult返回类型   
	 * @author ShenZiYang
	 * @date 2017年12月26日下午4:34:31
	 * @throws
	 */
	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public @ResponseBody RetResult saveUser(TbUserVO vo){
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("根据ID获取用户信息开始:code={},message={}",code,message);
		
		TbUser newUser = null;
		try{
			
			newUser = tbUserServuce.saveUser(vo);
			
		}catch(Exception e){
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("根据ID获取用户信息异常:code={},message={},e={}", code, message, e);
		}
		GwsLogger.info("根据ID获取用户信息结束,code={},message={}", code, JSON.toJSONString(newUser));
		return RetResult.setRetDate(code, message, newUser);
	}
	
	
	/**
     * 
     * (根据ID获取用户信息) 
     * @Title getUserById 
     * @param userId
     * @return RetResult返回类型   
     * @author ShenZiYang
     * @date 2017年12月26日下午3:56:45
     * @throws 异常
     */
	@RequestMapping(value = "/getUserById", method = RequestMethod.POST)
	public @ResponseBody RetResult getUserById(Long userId) {
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("根据ID获取用户信息开始:code={},message={}",code,message);
		
		if(null == userId || userId < 1){
			GwsLogger.error("传入的用户ID为空:userId={}", userId);
			return RetResult.setRetDate(CommConstant.GWSCOD0006, CommConstant.GWSMSG0006,null);
		}
		
		TbUser user = null;
		try{
			
			user = tbUserServuce.getUserById(userId);
			
		}catch(Exception e){
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("根据ID获取用户信息异常:code={},message={},e={}", code, message, e);
		}
		
		GwsLogger.info("根据ID获取用户信息结束,code={},message={}", code, JSON.toJSONString(user));
		return RetResult.setRetDate(code, message, user);
	}
	
	
	
}
