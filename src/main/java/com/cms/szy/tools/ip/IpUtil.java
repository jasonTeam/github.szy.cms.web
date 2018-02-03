package com.cms.szy.tools.ip;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

public class IpUtil {

	/**
	 * 
	 * 【获取id】
	 * 
	 * @author liunan 2016年5月9日
	 * @return
	 */
//	public static Long getSeq() {
//		Long maxNum = System.currentTimeMillis();
//		Long randNum = Math.round(Math.random() * 1000);
//		return maxNum * 1000 + randNum;
//	}
	
	//modify by szy 2018.1.23
	//项目启动时Redis从这里获取表的id值初始值
	public static Long getSeq() {
		Long initNum = 10001L;
		return initNum;
	}
	
	
	  public static String getIpAddr(HttpServletRequest request) {
		    if (null == request) {
		      return null;
		    }
		    String proxs[] = { "X-Forwarded-For" };
		    String ip = request.getHeader(proxs[0]);
		    if (StringUtils.isEmpty(ip)) {
		       return request.getRemoteAddr();
		    }
		    return ip;
		  }
}
