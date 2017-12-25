package com.cms.szy.configuration.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cms.szy.configuration.log.GwsLogger;
import com.cms.szy.entity.po.TbMenu;
import com.cms.szy.entity.po.TbUser;
import com.cms.szy.service.TbMenuService;
import com.cms.szy.tools.constant.SessionConstant;
import com.cms.szy.tools.role.RightsHelper;


public class RightsHandlerInterceptor extends HandlerInterceptorAdapter{
	
  	@Autowired
  	private TbMenuService tbMenuService;
  	
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		String path = request.getServletPath();
		if (path.matches(SessionConstant.NO_INTERCEPTOR_PATH))
			return true;
		HttpSession session = request.getSession();
		TbUser user = (TbUser) session.getAttribute(SessionConstant.SESSION_USER);
		Integer menuId = null;
		List<TbMenu> subList = tbMenuService.listAllSubMenu();
		for (TbMenu m : subList) {
			String menuUrl = m.getMenuUrl();
			if (StringUtils.isNotEmpty(menuUrl)) {
				if (path.contains(menuUrl)) {
					menuId = m.getMenuId();
					break;
				} else {
					String[] arr = menuUrl.split("\\.");
					String regex = "";
					if (arr.length == 2) {
						regex = "/?" + arr[0] + "(/.*)?." + arr[1];
					} else {
						regex = "/?" + menuUrl + "(/.*)?.html";
					}
					if (path.matches(regex)) {
						menuId = m.getMenuId();
						break;
					}
				}
			}
		}
		
		GwsLogger.info("权限拦截路径和菜单id:path={},menuId={}",path,menuId);
		if (menuId != null) {
			// user =  ServiceHelper.getUserService().getUserAndRoleById(user.getUserId());
			String userRights = (String) session.getAttribute(SessionConstant.SESSION_USER_RIGHTS);
			String roleRights = (String) session.getAttribute(SessionConstant.SESSION_ROLE_RIGHTS);
			if (RightsHelper.testRights(userRights, menuId)|| RightsHelper.testRights(roleRights, menuId)) {
				return true;
			} else {
				GwsLogger.info("用户访问路径被阻止:userName={},path={}",user.getUserName(),path);
//				ModelAndView mv = new ModelAndView();
//				mv.setViewName("common/noRights");
//				throw new ModelAndViewDefiningException(mv);
				//response.sendRedirect(request.getContextPath()+ "/noRights");
				response.setStatus(403);
				return false;
			}
		}
		return super.preHandle(request, response, handler);
	}
	
}
