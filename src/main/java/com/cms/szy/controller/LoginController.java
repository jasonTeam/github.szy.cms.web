package com.cms.szy.controller;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cms.szy.service.TbUserService;
import com.cms.szy.tools.result.RetResult;
import com.cms.szy.tools.shiro.ShiroUtils;



/**
 * 
 * (登录控制器) 
 * @ClassName LoginController 
 * @author ShenZiYang 
 * @date 2017年12月19日 上午9:35:29
 */
@Controller
public class LoginController {
	
	@Autowired
	TbUserService tbUserServuce;
	
	/**
	 * 
	 * (登录) 
	 * @Title login 
	 * @param vo
	 * @return RetResult返回类型   
	 * @author ShenZiYang
	 * @date 2017年12月19日上午9:49:35
	 * @throws 登录异常
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody RetResult login(String userName,String password) {
				
		try {
			
			Subject subject = ShiroUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
			subject.login(token);
			
		} catch (UnknownAccountException e) {
			return RetResult.setRetDate("1101", e.getMessage(),null);
		} catch (IncorrectCredentialsException e) {
			return RetResult.setRetDate("1111","账号或密码不正确",null);
		} catch (LockedAccountException e) {
			return RetResult.setRetDate("1111","账号已被锁定,请联系管理员",null);
		} catch (AuthenticationException e) {
			return RetResult.setRetDate("1111","账户验证失败",null);
		}
		
		return RetResult.setRetDate("1101", "success",null);
	}
	
	
	
	
	
}	
