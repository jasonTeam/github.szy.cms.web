package com.cms.szy.tools.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import com.cms.szy.entity.po.User;

/**
 * 
 *【Shiro工具类】
 * @ClassName ShiroUtils 
 * @author ShenZiYang 
 * @date 2018年1月21日 下午3:47:43
 */
public class ShiroUtils {
	
	/**  加密算法 */
	public final static String hashAlgorithmName = "SHA-256";
	
	/**  循环次数 */
	public final static int hashIterations = 16;
			
	public static String sha256(String password, String salt) {
		return new SimpleHash(hashAlgorithmName, password, salt, hashIterations).toString();
	}
	
	//获取Session
	public static Session getSession() {
		return SecurityUtils.getSubject().getSession();
	}
	
	//Shiro的subject实质上是当前执行用户的特定视图。
	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}
	
	//获取用户实体对象
	public static User getUserEntity() {
		return (User)SecurityUtils.getSubject().getPrincipal(); 
	}
	
	//获取登录用户ID
	public static Long getUserId() {
		return getUserEntity().getUserId();
	}
	
	public static void setSessionAttribute(Object key, Object value) {
		getSession().setAttribute(key, value);
	}

	public static Object getSessionAttribute(Object key) {
		return getSession().getAttribute(key);
	}
	
	//是否登录
	public static boolean isLogin() {
		return SecurityUtils.getSubject().getPrincipal() != null;
	}
	
	//登出
	public static void logout() {
		SecurityUtils.getSubject().logout();
	}
	
	public static String getKaptcha(String key) throws Exception{
		Object kaptcha = getSessionAttribute(key);
		if(kaptcha == null){
			throw new Exception("验证码已失效");
		}
		getSession().removeAttribute(key);
		return kaptcha.toString();
	}

}
