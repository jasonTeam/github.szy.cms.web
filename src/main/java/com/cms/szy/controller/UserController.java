package com.cms.szy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cms.szy.service.UserService;
import com.cms.szy.tools.result.Ret;
import com.cms.szy.tools.shiro.ShiroUtils;


/**
 * 
 * (用户控制类) 
 * @ClassName UserController 
 * @author ShenZiYang 
 * @date 2018年1月6日 下午12:30:09
 */
@Controller
@RequestMapping("/sys/user")
public class UserController extends AbstractController{
	
	@Autowired
	private UserService userService;
	

	/**
	 * 
	 * (获取登录用户信息) 
	 * @Title userInfo 
	 * @return Ret返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月8日下午2:15:18
	 * @throws 异常
	 */
	@RequestMapping(value = "/userInfo", method = RequestMethod.GET)
	public @ResponseBody Ret userInfo(){
		return Ret.ok().put("user", getUser());
	}
	
	
	//分页查询所有用户列表
	
	
	/**
	 * 
	 * (修改密码) 
	 * @Title modifyPwd 
	 * @param oriPassword
	 * @param newPassword
	 * @return Ret返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月8日下午2:44:18
	 * @throws
	 */
	public Ret modifyPwd(String oriPassword, String newPassword) {

		oriPassword = ShiroUtils.sha256(oriPassword, getUser().getSalt()); // 原密码
		newPassword = ShiroUtils.sha256(newPassword, getUser().getSalt()); // 新密码

		try {

			int res = userService.updatePwd(getUserId(), oriPassword, newPassword);
			if (res < 1) {
				return Ret.error("密码修改失败!");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return Ret.ok();

	}
	
	
	
	
//	@RequestMapping(value = "/getPermsByUser", method = RequestMethod.POST)
//	public @ResponseBody RetResult getPermsByUser(Long userId){
//		
//		List<String> listPerms = userService.getPermsByUser(userId);
//		
//		return RetResult.setRetDate("0000", "success", listPerms);
//		
//	}
	
	
	
	
	
	
	
}
