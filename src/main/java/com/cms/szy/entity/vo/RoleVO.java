package com.cms.szy.entity.vo;

import java.io.Serializable;

/**
 * 
 * (值对象)
 * 
 * @ClassName RoleVO
 * @author ShenZiYang
 * @date 2018年1月11日 下午4:44:08
 */
public class RoleVO implements Serializable {

	private static final long serialVersionUID = 2300429869844098481L;

	private Integer pageNo; // 当前页数
	private Integer pageSize; // 每页条数
	
	private String roleName;
	
	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	
	

}
