package com.cms.szy.entity.vo;

import java.io.Serializable;

/**
 * 
 * (值对象) 
 * @ClassName UserVO 
 * @author ShenZiYang 
 * @date 2018年1月8日 下午4:01:27
 */
public class UserVO implements Serializable{
	
	private static final long serialVersionUID = 3499715895651181190L;
	

	private String userName; //用户名
	
	private int page; //当前页数
	 
	private int limit; //每页条数
	
	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	
	
}
