package com.cms.szy.configuration.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cms.szy.configuration.log.GwsLogger;
import com.cms.szy.entity.po.Menu;
import com.cms.szy.entity.po.User;
import com.cms.szy.service.MenuService;
import com.cms.szy.tools.constant.SessionConstant;

/**
 * 
 *【权限拦截器实现】  
 * @author ShenZiYang 
 * @date 2018年2月7日 上午11:24:14
 */
public class RightsHandlerInterceptor extends HandlerInterceptorAdapter{
	
	@Autowired
	private MenuService menuService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
		String visitPath = request.getServletPath();  //获取访问路径
		if(visitPath.matches(SessionConstant.NO_INTERCEPTOR_PATH)){
			return true;
		}
		
		//获取请求中的session
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute(SessionConstant.SESSION_USER);  //Object强转为User实体信息
		
		Long menuId = null;  //菜单ID
		List<Menu> menuList = menuService.queryAllChildMenu(); //获取所有的二级菜单集合
		for(Menu menu : menuList){
			String menuUrl = menu.getMenuUrl();  //获取二级菜单链接
			//下面将匹配请求的路径如果包含获取其菜单链接的菜单ID
			if(StringUtils.isNotBlank(menuUrl)){
				if(visitPath.contains(menuUrl)){
					menuId = menu.getMenuId();
					break; //跳出整个循环
				}else{
					String[] urlArr = menuUrl.split("\\."); // 以 \\. 分割字符串将结果保存到字符串数组中
					String regex = "";
					if(urlArr.length == 2){
						regex = "/?" + urlArr[0] + "(/.*)?." + urlArr[1];
					}else{
						regex = "/?" + menuUrl + "(/.*)?.html";
					}
					if(menuUrl.matches(regex)){
						menuId = menu.getMenuId();
						break;
					}
				}
			}
		}
		
		GwsLogger.info("权限拦截路径和菜单id:path={},menuId={}",visitPath,menuId);
		if(null != menuId){
			
		}
		return super.preHandle(request, response, handler);
		
		
	}
	
	
	
	
}
