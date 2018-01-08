package com.cms.szy.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cms.szy.entity.po.Menu;
import com.cms.szy.service.MenuService;
import com.cms.szy.tools.result.Ret;
import com.cms.szy.tools.result.RetResult;


/**
 * 
 * (菜单控制类) 
 * @ClassName MenuController 
 * @author ShenZiYang 
 * @date 2018年1月6日 上午10:20:17
 */

@Controller
@RequestMapping("/sys/menu")
public class MenuController extends AbstractController{
	
	@Autowired
	private MenuService menuService;
	
	
	/**
	 * 导航菜单
	 */
	@RequestMapping(value = "/nav",method = RequestMethod.GET)
	public @ResponseBody Ret nav(){
		List<Menu> meunList = menuService.getUserMenuList(1L);
		return Ret.ok().put("menuList", meunList);
	}
	
	
	/**
	 * 
	 * (查询所有菜单列表) 
	 * @Title list 
	 * @return RetResult返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月6日下午1:28:19
	 * @throws 查询失败
	 */
	@RequestMapping(value = "/list",method = RequestMethod.POST)
	@RequiresPermissions("sys:menu:list")
	public String list(){
		List<Menu> menuList = menuService.menuList();
		return null;
	}
	
	
	
	
}
