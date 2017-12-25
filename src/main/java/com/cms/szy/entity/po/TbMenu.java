package com.cms.szy.entity.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * (菜单实体表) 
 * @ClassName TbMenu 
 * @author ShenZiYang 
 * @date 2017年12月19日 上午10:39:12
 */
@Entity
@Table(name = "tb_menu")
public class TbMenu implements Serializable{
	
	private static final long serialVersionUID = -5872662851353250549L;

	@Id
	@Column(name = "menu_id")
	private Integer menuId;
	
	@Column(name = "menu_name")
	private String menuName;
	
	@Column(name = "menu_url")
	private String menuUrl;
	
	@Column(name = "parent_id")
	private Long parentId;
	
	@Column(name = "sort")
	private Integer sort;
	
	@Column(name = "ctime")
	private Integer ctime;
	
	@Column(name = "utime")
	private Integer utime;
	
	@Column(name = "is_delete")
	private Short isDelete;
	

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
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

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getCtime() {
		return ctime;
	}

	public void setCtime(Integer ctime) {
		this.ctime = ctime;
	}

	public Integer getUtime() {
		return utime;
	}

	public void setUtime(Integer utime) {
		this.utime = utime;
	}

	public Short getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Short isDelete) {
		this.isDelete = isDelete;
	}
	
			
}
