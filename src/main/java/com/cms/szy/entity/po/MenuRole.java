package com.cms.szy.entity.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * (菜单角色关联表实体类)
 * 
 * @ClassName MenuRole
 * @author ShenZiYang
 * @date 2018年1月8日 下午1:39:02
 */

@Entity
@Table(name = "sys_menu_role")
public class MenuRole implements Serializable {

	private static final long serialVersionUID = 1319457408415187048L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "role_id")
	private Long roleId;

	@Column(name = "menu_id")
	private Long menuId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}
	

}
