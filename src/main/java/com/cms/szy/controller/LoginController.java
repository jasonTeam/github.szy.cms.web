package com.cms.szy.controller;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cms.szy.configuration.log.GwsLogger;
import com.cms.szy.tools.constant.CommConstant;
import com.cms.szy.tools.result.Ret;
import com.cms.szy.tools.shiro.ShiroUtils;

/**
 * 
 * (登录控制器)
 * 
 * @ClassName LoginController
 * @author ShenZiYang
 * @date 2017年12月19日 上午9:35:29
 */

@Controller
public class LoginController extends AbstractController {

	/**
	 * 
	 * 【用户登录】
	 * 
	 * @Title login
	 * @param userName
	 * @param password
	 * @return Ret返回类型
	 * @author ShenZiYang
	 * @date 2018年1月21日下午3:48:25
	 * @throws 异常
	 */
	@RequestMapping(value = "/sys/login", method = RequestMethod.POST)
	public @ResponseBody Ret login(String userName, String password) {
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		Long startTime = System.currentTimeMillis();
		GwsLogger.info("用户登录开始:code={},message={},startTime={}", code, message, startTime);

		try {
			Subject subject = ShiroUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
			subject.login(token);
		} catch (UnknownAccountException e) {
			GwsLogger.error(e.getMessage(), "");
			return Ret.error(e.getMessage());
		} catch (IncorrectCredentialsException e) {
			GwsLogger.error(e.getMessage(), "");
			return Ret.error("账号或密码不正确");
		} catch (LockedAccountException e) {
			GwsLogger.error("账号已被锁定,请联系管理员", "");
			return Ret.error("账号已被锁定,请联系管理员");
		}

		Long endTime = System.currentTimeMillis() - startTime;
		GwsLogger.info("用户登录结束:code={},message={},endTime={}", code, message, endTime);
		return Ret.ok();
	}

	/**
	 * 
	 * 【退出登录】
	 * 
	 * @return String返回类型
	 * @author ShenZiYang
	 * @date 2018年1月19日上午10:18:26
	 * @throws 异常
	 */
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout() {
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("退出系统开始:code={},message={}", code, message);

		try {
			ShiroUtils.logout();
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("退出系统异常:code={},message={},e={}", code, message, e);
		}

		GwsLogger.info("退出系统结束:code={},message={}", code, message);
		return INDEX;
	}

}
