package com.cms.szy.controller;

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

import com.cms.szy.configuration.log.GwsLogger;
import com.cms.szy.service.TbUserService;
import com.cms.szy.tools.result.Ret;
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
	 * (登录页面) 
	 * @Title hello 
	 * @return String返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月6日下午5:28:26
	 * @throws
	 */
	@RequestMapping("/login")
	public String hello(){
		return "login";
	}
	
	
	
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
	@RequestMapping(value = "/sys/login", method = RequestMethod.POST)
	public @ResponseBody Ret login(String userName, String password) {

		try {
			Subject subject = ShiroUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
			subject.login(token);
		} catch (UnknownAccountException e) {
			return Ret.error(e.getMessage());
		} catch (IncorrectCredentialsException e) {
			return Ret.error("账号或密码不正确");
		} catch (LockedAccountException e) {
			return Ret.error("账号已被锁定,请联系管理员");
		} 

		return Ret.ok();
	}
	
	
	/**
	 * 
	 *【退出登录】 
	 * @return String返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月19日上午10:18:26
	 * @throws 异常
	 */
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout() {
		ShiroUtils.logout();
		GwsLogger.info("当前用户退出登录", "logout");
		return "redirect:login.html";
	}
	
	
}	
