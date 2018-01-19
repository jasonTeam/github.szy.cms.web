package com.cms.szy.entity.dto;

import java.io.Serializable;

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

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	
	
	
	
}
