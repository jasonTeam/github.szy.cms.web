package com.cms.szy.entity.dto;

import java.io.Serializable;
import java.util.List;

public class MenuDTO implements Serializable{

	private static final long serialVersionUID = -2550288073759363761L;
	
	/**
	 * ztree属性
	 */
	private Boolean open;
	
	private List<?> list;

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
