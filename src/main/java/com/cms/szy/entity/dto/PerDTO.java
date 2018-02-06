package com.cms.szy.entity.dto;

import java.io.Serializable;

public class PerDTO implements Serializable{

	private static final long serialVersionUID = 6387557364254838361L;
	
	/*** 用户ID */
	private Long userId;
	
	/*** 菜单ID */
	private Long menuId;
	
	/*** 角色ID */
	private Long roleId;

	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	
	
	
	
	
}
