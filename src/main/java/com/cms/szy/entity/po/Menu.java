package com.cms.szy.entity.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cms.szy.entity.dto.MenuDTO;

/**
 * 
 * (菜单实体类) 
 * @ClassName Menu 
 * @author ShenZiYang 
 * @date 2018年1月6日 上午9:39:08
 */
@Entity
@Table(name = "sys_menu") 
public class Menu extends MenuDTO implements Serializable{
	
	private static final long serialVersionUID = -900324715261758027L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "menu_id")
	private Long menuId;
	
	/**
	 * 父菜单ID，一级菜单为0
	 */
	@Column(name = "parent_id")
	private Long parentId;
	
	/**
	 * 菜单名称
	 */
	@Column(name = "name")
	private String name;
	
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
	
	@Column(name = "sort")
	private Integer sort;
	
	

	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	
	
	
	
}
