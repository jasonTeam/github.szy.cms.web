package com.cms.szy.entity.dto;

import java.io.Serializable;
import java.util.List;

public class UserDTO implements Serializable{
	
	private static final long serialVersionUID = 4388246100927112109L;
	
	private String deptName; //部门名称
	
	private String roleName; //角色名称
	
	private List<Long> roleIdList; //角色ID列表
	

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<Long> getRoleIdList() {
		return roleIdList;
	}

	public void setRoleIdList(List<Long> roleIdList) {
		this.roleIdList = roleIdList;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	
}
