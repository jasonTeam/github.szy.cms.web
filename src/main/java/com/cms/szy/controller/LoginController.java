package com.cms.szy.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cms.szy.configuration.annotation.JsonParam;
import com.cms.szy.entity.po.TbUser;
import com.cms.szy.entity.vo.TbUserVO;
import com.cms.szy.service.TbUserService;
import com.cms.szy.tools.result.RetResult;

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
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public @ResponseBody RetResult login(HttpSession session, @JsonParam TbUserVO vo, HttpServletRequest request) {
		TbUser user = tbUserServuce.login(vo.getUserName(), vo.getUserPwd());
		return RetResult.setRetDate("1101", "success", user);
	}
	
	
	
}	
