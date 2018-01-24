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
	
	private int pageNo; //当前页数
	 
	private int pageSize; //每页条数
	
	private String order; //排序
	
	private String sidx; //点击时传入的需要排序字段
	

	public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	
}
