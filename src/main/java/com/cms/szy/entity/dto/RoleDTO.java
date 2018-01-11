package com.cms.szy.entity.dto;

import java.io.Serializable;

public class RoleDTO implements Serializable{
	
	private static final long serialVersionUID = -942135735152469564L;
	
	private String deptName; //部门名称

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	
	
	
}
