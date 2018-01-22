package com.cms.szy.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cms.szy.configuration.log.GwsLogger;
import com.cms.szy.entity.po.Menu;
import com.cms.szy.enums.MenuTypeEnum;
import com.cms.szy.service.MenuService;
import com.cms.szy.tools.constant.CommConstant;
import com.cms.szy.tools.constant.Constant;
import com.cms.szy.tools.exception.RRException;
import com.cms.szy.tools.result.Ret;


/**
 * 
 * (菜单管理) 
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
	 * 
	 *【菜单导航】 
	 * @return Ret返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月20日下午5:01:10
	 * @throws 异常
	 */
	@RequestMapping(value = "/nav",method = RequestMethod.GET)
	public @ResponseBody Ret nav(){
		List<Menu> meunList = menuService.getUserMenuList(1L);
		return Ret.ok().put("menuList", meunList);
	}
	
	
	/**
	 * 
	 *【查询所有的菜单列表】 
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
	

	/**
	 * 
	 *【选择菜单(添加、修改菜单)】 
	 * @return Ret返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月20日下午5:01:30
	 * @throws 异常
	 */
	@RequestMapping("/select")
	@RequiresPermissions("sys:menu:select")
	public Ret select(){
		//查询列表数据
		List<Menu> menuList = menuService.menuList();
		
		//添加顶级菜单
		Menu root = new Menu();
		root.setMenuId(0L);
		root.setName(Constant.LEVEL_MENU);
		root.setParentId(-1L);
		root.setOpen(true);
		menuList.add(root);
		
		return Ret.ok().put("menuList", menuList);
	}
	
	
	/**
	 * 
	 *【菜单信息,用户菜单修改页面回显】 
	 * @param menuId
	 * @return R返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月20日下午5:02:17
	 * @throws 异常
	 */
	@RequestMapping("/info/{menuId}")
	@RequiresPermissions("sys:menu:info")
	public Ret info(@PathVariable("menuId") Long menuId){
		Menu menu = menuService.queryMenuByMenuId(menuId);
		return Ret.ok().put("menu", menu);
	}
	
	
	/**
	 * 
	 *【新增菜单】 
	 * @param menu
	 * @return R返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月20日下午5:08:56
	 * @throws 异常
	 */
	@RequestMapping("/save")
	@RequiresPermissions("sys:menu:save")
	public Ret save(@RequestBody Menu menu){
		//数据校验
		verifyForm(menu); 
		menuService.saveMenu(menu);
		return Ret.ok();
	}
	

	/**
	 * 
	 *【修改菜单】 
	 * @param menu
	 * @return R返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月20日下午5:09:22
	 * @throws 异常
	 */
	@RequestMapping("/update")
	@RequiresPermissions("sys:menu:update")
	public Ret update(@RequestBody Menu menu){
		
		verifyForm(menu); //数据校验
		menuService.updateMenu(menu);
		
		return Ret.ok();
	}
	

	/**
	 * 
	 *【删除菜单】 
	 * @param menuId
	 * @return R返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月20日下午5:09:40
	 * @throws 异常
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("sys:menu:delete")
	public Ret delete(long menuId){
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("删除菜单操作开始:code={},message={}",code,message);
		
		//参数校验
		if(menuId <= 31){
			return Ret.error("系统菜单，不能删除");
		}
		
		//删除时判断是否还有子菜单或按钮
		List<Menu> menuList = menuService.queryListParentId(menuId);
		if(null != menuList && menuList.size() > 0){
			return Ret.error("请先删除子菜单或按钮");
		}
		
		try{
			menuService.deleteMenuBatch(new Long[]{menuId});
		}catch(Exception e){
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("删除菜单操作异常:code={},message={},e={}", code, message, e);
		}
		
		GwsLogger.info("删除菜单操作结束,code={},message={}", code, message);
		return Ret.ok();
	}
	
	
	/**
	 * 验证参数是否正确
	 */
	private void verifyForm(Menu menu){
		if(StringUtils.isBlank(menu.getName())){
			throw new RRException("菜单名称不能为空");
		}
		
		if(menu.getParentId() == null){
			throw new RRException("上级菜单不能为空");
		}
		
		//菜单
		if(menu.getType() == MenuTypeEnum.MENU.getVal()){
			if(StringUtils.isBlank(menu.getMenuUrl())){
				throw new RRException("菜单URL不能为空");
			}
		}
		
		//上级菜单类型
		int parentType = MenuTypeEnum.CATALOG.getVal();
		if(menu.getParentId() != 0){
			//根据上级菜单查询菜单实体
			Menu parentMenu = menuService.queryChildMenuId(menu.getParentId());
			parentType = parentMenu.getType();
		}
		
		//目录、菜单
		if(menu.getType() == MenuTypeEnum.CATALOG.getVal() || menu.getType() == MenuTypeEnum.CATALOG.getVal()){
			if(parentType != MenuTypeEnum.CATALOG.getVal()){
				throw new RRException("上级菜单只能为目录类型");
			}
			return ;
		}
		
		//按钮
		if(menu.getType() == MenuTypeEnum.BUTTON.getVal()){
			if(parentType != MenuTypeEnum.MENU.getVal()){
				throw new RRException("上级菜单只能为菜单类型");
			}
			return ;
		}
	}
	
	
	
}
