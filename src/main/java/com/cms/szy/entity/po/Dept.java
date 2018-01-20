package com.cms.szy.entity.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cms.szy.entity.dto.DeptDTO;

/**
 * 
 * (部门实体类) 
 * @ClassName Dept 
 * @author ShenZiYang 
 * @date 2018年1月8日 下午1:31:46
 */
@Entity
@Table(name = "sys_dept") 
public class Dept extends DeptDTO implements Serializable{
	

	private static final long serialVersionUID = -6974614561905608885L;

	@Id
	@Column(name = "dept_id")
	private Long deptId;
	
	/**
	 * 上级部门ID，一级部门为0
	 */
	@Column(name = "parent_id")
	private Long parentId;
	
	/**
	 * 部门名称
	 */
	@Column(name = "name")
	private String name;
	
	@Column(name = "order_num")
	private Integer orderNum;
	
	/**
	 * 是否删除 1：正常，-1：已删除
	 */
	@Column(name = "is_delete")
	private  Short isDelete;


	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
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

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public Short getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Short isDelete) {
		this.isDelete = isDelete;
	}
	
	
}
