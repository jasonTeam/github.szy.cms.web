package com.cms.szy.configuration.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cms.szy.tools.constant.SessionConstant;



/**
 * 
 * 【登录拦截器】
 *
 * @version
 * @author liunan 2016年4月11日 下午5:50:28
 *
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String path = request.getServletPath();
		if (path.matches(SessionConstant.NO_INTERCEPTOR_PATH)) {
			return true;
		} else {
			HttpSession session = request.getSession();
			Object obj =session.getAttribute(SessionConstant.SESSION_USER);
			if (obj != null) {
				
				// 输出接口访问日志 add by szy 2017.11.08
//				String content = getLogContent(request, obj);
//				GwsLogger.interfaceLogInfo(content);
				
				return true;
			} else {
				response.sendRedirect(request.getContextPath()+ "/login");
				return false;
			}
		}
	}
	
	/**
	 * @Description (获取接口访问日志信息) 
	 * @Title getLogContent 
	 * @param request
	 * @param obj
	 * @return String返回类型   
	 * @author SZY
	 * @date 2017年11月8日下午4:22:56
	 * @throws
	 */
//	private String getLogContent(HttpServletRequest request, Object obj) {
//		String userLoginIp = GetIpAddr.getIpAddr(request);
//		AdminInfo admin = (AdminInfo)obj;
//		StringBuffer sb = new StringBuffer();
//		sb.append("请求IP: " + userLoginIp + "--");
//		sb.append("用户名:" + admin.getUserName() + "--");
//		sb.append("请求时间:" + DateUtil.turnIntegerToString(DateUtil.currentSecond()) + "--");
//		sb.append("请求接口:" + request.getRequestURI() + "--");
//		// 获取请求的参数
//		Enumeration<String> parameterNames = request.getParameterNames();
//		sb.append("请求参数【params:】 ");
//		while(parameterNames.hasMoreElements()){
//			String parameterName = parameterNames.nextElement();
//			String[] arr = request.getParameterValues(parameterName);
//			for(int i = 0; i<arr.length; i++){
//				sb.append(parameterName +" = "+ arr[i] + ",");
//			}
//		}
//		return sb.toString();
//	}

}