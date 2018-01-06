package com.cms.szy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cms.szy.service.UserService;
import com.cms.szy.tools.result.RetResult;

/**
 * 
 * (用户控制类) 
 * @ClassName UserController 
 * @author ShenZiYang 
 * @date 2018年1月6日 下午12:30:09
 */
@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/getPermsByUser", method = RequestMethod.POST)
	public @ResponseBody RetResult getPermsByUser(Long userId){
		
		List<String> listPerms = userService.getPermsByUser(userId);
		
		return RetResult.setRetDate("0000", "success", listPerms);
		
	}
	
	
}
