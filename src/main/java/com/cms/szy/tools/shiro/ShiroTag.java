package com.cms.szy.tools.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;

/**
 * 
 *【Shiro权限标签】  
 * @author ShenZiYang 
 * @date 2018年1月22日 上午10:09:24
 */
@Component
public class ShiroTag {

	/**
	 * 是否拥有该权限
	 * @param permission  权限标识
	 * @return   true：是     false：否
	 */
	public boolean hasPermission(String permission) {
		Subject subject = SecurityUtils.getSubject();
		return subject != null && subject.isPermitted(permission);
	}

}
