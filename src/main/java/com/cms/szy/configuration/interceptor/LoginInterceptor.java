package com.cms.szy.configuration.interceptor;



import java.io.OutputStream;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cms.szy.configuration.redis.cache.CacheClient;
import com.cms.szy.entity.po.User;
import com.cms.szy.tools.constant.CommConstant;
import com.cms.szy.tools.constant.SessionConstant;
import com.cms.szy.tools.date.DateUtil;
import com.cms.szy.tools.result.RetResult;


/**
 * 
 * 【登录拦截器】
 *
 * @version
 * @author liunan 2016年4月11日 下午5:50:28
 *
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private CacheClient cache;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String path = request.getServletPath();
		if (path.matches(SessionConstant.NO_INTERCEPTOR_PATH)) {
			return true;
		} else {
			String token = request.getParameter("token");
			// 没有token、登录信息，返回错误码
			if(!checkToken(token)) {
				RetResult result = RetResult.setRetDate(CommConstant.GWSCOD0002, /*CommConstant.GWSMSG0002*/"token验证失败，请重新登录", null);
				OutputStream ps = response.getOutputStream();
				try {
					response.setContentType("application/json; charset=utf-8");
					JSONObject resultJson = JSON.parseObject(JSON.toJSONString(result));
					ps.write(resultJson.toString().getBytes("UTF-8"));
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					ps.flush();
					ps.close();
				}
				return false;
			}
			
			// 有登录状态信息
//			JSONObject adminObject = JSON.parseObject(cache.get(CommConstant.CHANNEL_ADMIN_MANAGE_IS_LOGIN, token.substring(0, 11)).toString());
//			String adminMobile = adminObject.get("adminMobile").toString();
			
			//登录成功后更新token
//			cache.set(CHANNEL_ADMIN_MANAGE_IS_LOGIN, adminMobile, JSON.toJSONString(adminObject), CHANNEL_ADMIN_MANAGE_IS_LOGIN_TIMEOUT);
			
			// 输出接口访问日志
//			String content = getLogContent(request,adminObject);
//			GwsLogger.interfaceLogInfo(content);
			
			return true;
		}
	}
	
	/**
	 * 【token校验】
	 * @author xwf 2017年12月4日
	 * @param token
	 * @return
	 */
	private boolean checkToken(String token) {
		
//		if (null == token || null == cache.get(CHANNEL_ADMIN_MANAGE_IS_LOGIN, token.substring(0, 11))) {
//			return false;
//		}
//		JSONObject adminObject = JSON.parseObject(cache.get(CHANNEL_ADMIN_MANAGE_IS_LOGIN, token.substring(0, 11)).toString());
//		String tokenInRedis = adminObject.get("token").toString();
//		if (!token.equals(tokenInRedis)) {
//			return false;
//		}
		
		return true;
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
	private String getLogContent(HttpServletRequest request, Object obj) {
		//String userLoginIp = GetIpAddr.getIpAddr(request);
		String userLoginIp = "127.0.0.1";
		User admin = (User)obj;
		StringBuffer sb = new StringBuffer();
		sb.append("请求IP: " + userLoginIp + "--");
		sb.append("用户名:" + admin.getUserName() + "--");
		sb.append("请求时间:" + DateUtil.turnIntegerToString(DateUtil.currentSecond()) + "--");
		sb.append("请求接口:" + request.getRequestURI() + "--");
		// 获取请求的参数
		Enumeration<String> parameterNames = request.getParameterNames();
		sb.append("请求参数【params:】 ");
		while(parameterNames.hasMoreElements()){
			String parameterName = parameterNames.nextElement();
			String[] arr = request.getParameterValues(parameterName);
			for(int i = 0; i<arr.length; i++){
				sb.append(parameterName +" = "+ arr[i] + ",");
			}
		}
		return sb.toString();
	}

}