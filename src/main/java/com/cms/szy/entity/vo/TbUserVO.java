package com.cms.szy.entity.vo;

import java.io.Serializable;

/**
 * 
 * (值对象,接受从页面传入的参数) 
 * @ClassName TbUserVO 
 * @author ShenZiYang 
 * @date 2017年12月19日 上午9:41:10
 */
public class TbUserVO implements Serializable{

	private static final long serialVersionUID = -3114848260412430221L;
	
	private String userName; //用户名
	private String userPwd;  //密码
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	
	
	
	
	
}
