package com.cms.szy.entity.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 
 *【部门值】  
 * @author ShenZiYang 
 * @date 2018年1月19日 下午1:27:47
 */
public class DeptDTO implements Serializable{

	private static final long serialVersionUID = -3370118605675676414L;
	
	//父级部门名称
	private String parentName;
	
	/*** ztree属性*/
	private Boolean open;

	private List<?> list;
	
	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}
	
}
