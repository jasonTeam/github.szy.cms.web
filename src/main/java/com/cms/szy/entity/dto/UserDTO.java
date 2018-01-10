package com.cms.szy.entity.dto;

import java.io.Serializable;

public class UserDTO implements Serializable{
	
	private static final long serialVersionUID = 4388246100927112109L;
	
	private String deptName; //部门名称

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	
}
