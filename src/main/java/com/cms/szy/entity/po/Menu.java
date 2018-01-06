package com.cms.szy.entity.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * (菜单实体类) 
 * @ClassName Menu 
 * @author ShenZiYang 
 * @date 2018年1月6日 上午9:39:08
 */
@Entity
@Table(name = "sys_menu") 
public class Menu {
	
	@Id
	@Column(name = "menu_id")
	private Long menuId;
	
	/**
	 * 父菜单ID，一级菜单为0
	 */
	@Column(name = "parent_id")
	private Long parentId;
	
	@Column(name = "menu_name")
	private String menuName;
	
	@Column(name = "menu_url")
	private String menuUrl;
	
	/**
	 * 授权(多个用逗号分隔，如：user:list,user:create)
	 */
	@Column(name = "perms")
	private String perms;
	
	/**
	 * 类型   0：目录   1：菜单   2：按钮
	 */
	@Column(name = "type")
	private Integer type;
	
	@Column(name = "icon")
	private String icon;
	
	@Column(name = "order")
	private Integer order;
	
	
	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getPerms() {
		return perms;
	}

	public void setPerms(String perms) {
		this.perms = perms;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}
	
	
	
}
