package com.cms.szy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cms.szy.entity.po.Menu;
import com.cms.szy.service.MenuService;
import com.cms.szy.tools.result.RetResult;

/**
 * 
 * (菜单控制类) 
 * @ClassName MenuController 
 * @author ShenZiYang 
 * @date 2018年1月6日 上午10:20:17
 */

@Controller
public class MenuController {
	
	@Autowired
	private MenuService menuService;
	
	@RequestMapping(value = "/menuList",method = RequestMethod.POST)
	public @ResponseBody RetResult menuList(){
	
		List<Menu> menuList = menuService.menuList();
		return RetResult.setRetDate("0000", "success", menuList);
	}
	
	
}
