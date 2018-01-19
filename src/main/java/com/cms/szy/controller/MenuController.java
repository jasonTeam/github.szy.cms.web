package com.cms.szy.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cms.szy.entity.po.Menu;
import com.cms.szy.service.MenuService;
import com.cms.szy.tools.result.Ret;


/**
 * 
 * (菜单控制类) 
 * @ClassName MenuController 
 * @author ShenZiYang 
 * @date 2018年1月6日 上午10:20:17
 */

@RestController
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
	 *【查询所有的菜单列表,功能权限也调用这个接口】 
	 * @return List<Menu>返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月19日下午12:37:07
	 * @throws 异常
	 */
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	@RequiresPermissions("sys:menu:list")
	public List<Menu> list(){
		List<Menu> menuList = menuService.menuList();
		return menuList;
	}
	
	
	
	
}
