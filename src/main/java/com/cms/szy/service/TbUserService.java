package com.cms.szy.service;


import com.cms.szy.entity.po.TbUser;

public interface TbUserService {
	
	
	/**
	 * 
	 * (用户登录) 
	 * @Title login 登录
	 * @param userName 用户名
	 * @param userPwd 密码
	 * @return TbUser返回类型   
	 * @author ShenZiYang
	 * @date 2017年12月18日下午5:39:37
	 * @throws 登录异常
	 */
	TbUser login(String userName,String userPwd);
	
	
	
}
