package com.cms.szy.entity.dto;

import java.io.Serializable;
import java.util.List;

public class RoleDTO implements Serializable{
	
	private static final long serialVersionUID = -942135735152469564L;
	
	//部门名称
	private String deptName; 
	
	// 菜单Id集合
	private List<Long> menuIdList;

	// 部门Id集合
	private List<Long> deptIdList;
	
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public List<Long> getMenuIdList() {
		return menuIdList;
	}

	public void setMenuIdList(List<Long> menuIdList) {
		this.menuIdList = menuIdList;
	}

	public List<Long> getDeptIdList() {
		return deptIdList;
	}

	public void setDeptIdList(List<Long> deptIdList) {
		this.deptIdList = deptIdList;
	}
	

	
}
